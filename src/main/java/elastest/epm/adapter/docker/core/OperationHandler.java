package elastest.epm.adapter.docker.core;

import com.google.protobuf.ByteString;
import elastest.epm.adapter.docker.Utils;
import elastest.epm.adapter.docker.exceptions.AdapterException;
import elastest.epm.adapter.docker.generated.*;
import elastest.epm.adapter.docker.model.*;
import elastest.epm.adapter.docker.model.Network;
import elastest.epm.adapter.docker.model.Package;
import elastest.epm.adapter.docker.model.VDU;
import elastest.epm.adapter.docker.repository.PackageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


@Component
public class OperationHandler extends OperationHandlerGrpc.OperationHandlerImplBase {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    Utils utils = new Utils();
    @Autowired private DockerHandler dockerHandler;
    @Autowired private PackageRepository packageRepository;

    @Override
    public void create(
            FileMessage request, io.grpc.stub.StreamObserver<ResourceGroupProto> responseObserver) {
        log.info("Deploying package...");
        try {
            ResourceGroup rg = Utils.extractResourceGroup(request.getFile().newInput());
            if(rg == null) throw new Exception("No Resource Group in Request!");

            LogstashConfig logstashConfig = new LogstashConfig();
            logstashConfig.setEnabled(false);
            logstashConfig.setAddress("");
            for( MetadataEntry entry : request.getMetadataList()) {
                if (entry.getKey().equals("enabled")) {
                    logstashConfig.setEnabled(Boolean.parseBoolean(entry.getValue()));
                }
                if (entry.getKey().equals("address"))
                    logstashConfig.setAddress(entry.getValue());
            }
            log.info(logstashConfig.toString());
            DockerCredentials dockerCredentials = Utils.getDockerCredentials(request.getFile().newInput());

            ResourceGroup output = dockerHandler.deployResourceGroup(rg, request.getPop(), logstashConfig, dockerCredentials);
            log.debug(String.valueOf(output));

            ArrayList<String> computeIds = new ArrayList<>();
            ArrayList<String> networkIds = new ArrayList<>();
            for(VDU vdu : output.getVdus()) computeIds.add(vdu.getComputeId());
            for(Network network : output.getNetworks()) networkIds.add(network.getNetworkId());
            Package p = new Package(); p.setComputeIds(computeIds); p.setNetworkIds(networkIds);
            p = packageRepository.save(p);
            log.info(String.valueOf(p));

            responseObserver.onNext(utils.translate(rg));
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.info("Could not create package: " + e.getMessage());
            responseObserver.onCompleted();
        }

    }

    @Override
    public void remove(
            TerminateMessage terminateMessage, io.grpc.stub.StreamObserver<Empty> responseObserver) {
        String vduId = terminateMessage.getVdu().getComputeId();
        Package packageDelete = null;
        for(Package p : packageRepository.findAll()){
            if( p.getComputeIds().contains(vduId)){
                packageDelete = p;
            }
        }
        if(packageDelete != null){
            dockerHandler.terminatePackage(packageDelete, terminateMessage.getPop());
        }
        Empty e = Empty.newBuilder().build();
        responseObserver.onNext(e);
        responseObserver.onCompleted();
    }

    @Override
    public void stop(
            ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {

        try {
            log.info("Stopping container: " + request.getResourceId());
            dockerHandler.stopContainer(request.getResourceId(), request.getPop());
            Empty e = Empty.newBuilder().build();
            responseObserver.onNext(e);
            responseObserver.onCompleted();
        } catch (AdapterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(
            ResourceIdentifier request, io.grpc.stub.StreamObserver<Empty> responseObserver) {
        try {
            log.info("Starting container: " + request.getResourceId());
            dockerHandler.startContainer(request.getResourceId(), request.getPop());
            Empty e = Empty.newBuilder().build();
            responseObserver.onNext(e);
            responseObserver.onCompleted();
        } catch (AdapterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void executeCommand(
            RuntimeMessage request, io.grpc.stub.StreamObserver<StringResponse> responseObserver) {
        try {
            String resourceId = request.getVdu().getComputeId();
            log.info("Executing command: " + request.getProperty(0) + " on container: " + resourceId);
            String response = dockerHandler.executeOnInstance(resourceId, request.getProperty(0), true, request.getPop());
            StringResponse stringResponse = StringResponse.newBuilder().setResponse(response).build();
            responseObserver.onNext(stringResponse);
            responseObserver.onCompleted();
        } catch (AdapterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadFile(
            RuntimeMessage request, io.grpc.stub.StreamObserver<FileMessage> responseObserver) {
        try {
            String resourceId = request.getVdu().getComputeId();
            log.info("Downloading file: " + request.getProperty(0) + " from " + resourceId);
            InputStream is = dockerHandler.downloadFileFromInstance(resourceId, request.getProperty(0), request.getPop());
            ByteString byteString = null;
            try {
                byteString = ByteString.readFrom(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileMessage f = FileMessage.newBuilder().setFile(byteString).build();
            responseObserver.onNext(f);
            responseObserver.onCompleted();
        } catch (AdapterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadFile(
            RuntimeMessage request, io.grpc.stub.StreamObserver<Empty> responseObserver) {

        String resourceId = request.getVdu().getComputeId();
        String type = request.getProperty(0);
        if(type.equals("withPath")){
            try {
                log.info("Uploading file: " + request.getProperty(1) + " to container: " + resourceId);
                dockerHandler.uploadFileToInstance(resourceId, request.getProperty(1), request.getProperty(2), request.getPop());
                Empty e = Empty.newBuilder().build();
                responseObserver.onNext(e);
                responseObserver.onCompleted();
            } catch (Exception e) {

                e.printStackTrace();
            }
        } else{
            InputStream is = new ByteArrayInputStream(request.getFile().toByteArray());
            try {
                log.info("Uploading file: " + type + " to container: " + resourceId);
                dockerHandler.uploadFileToInstance(resourceId, type, is, request.getPop());
                Empty e = Empty.newBuilder().build();
                responseObserver.onNext(e);
                responseObserver.onCompleted();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    @Override
    public void checkIfContainerExists(
            ResourceIdentifier request, io.grpc.stub.StreamObserver<StringResponse> responseObserver) {
        try {
            Boolean exists = dockerHandler.existsContainer(request.getResourceId(), request.getPop());
            StringResponse stringResponse = StringResponse.newBuilder().setResponse(exists.toString()).build();
            responseObserver.onNext(stringResponse);
            responseObserver.onCompleted();
        } catch (AdapterException e) {
            e.printStackTrace();
            StringResponse stringResponse = StringResponse.newBuilder().setResponse(String.valueOf(false)).build();
            responseObserver.onNext(stringResponse);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void checkIfContainerRunning(
            ResourceIdentifier request, io.grpc.stub.StreamObserver<StringResponse> responseObserver) {
        try {
            Boolean exists = dockerHandler.isRunningContainer(request.getResourceId(), request.getPop());
            StringResponse stringResponse = StringResponse.newBuilder().setResponse(exists.toString()).build();
            responseObserver.onNext(stringResponse);
            responseObserver.onCompleted();
        } catch (AdapterException e) {
            e.printStackTrace();
            StringResponse stringResponse = StringResponse.newBuilder().setResponse(String.valueOf(false)).build();
            responseObserver.onNext(stringResponse);
            responseObserver.onCompleted();
        }
    }*/
}
