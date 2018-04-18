package elastest.epm.adapter.docker;

import com.google.protobuf.ByteString;
import elastest.epm.adapter.docker.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class RuntimeTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    private OperationHandlerGrpc.OperationHandlerBlockingStub getClient() {

        ManagedChannelBuilder<?> channelBuilder =
                ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext(true);
        ManagedChannel channel = channelBuilder.build();
        return OperationHandlerGrpc.newBlockingStub(channel);
    }

    @Test
    public void deployPackage() throws IOException {

        PoP poP = PoP.newBuilder().setInterfaceEndpoint("unix:///var/run/docker.sock").build();

        OperationHandlerGrpc.OperationHandlerBlockingStub stub = getClient();
        FileInputStream f = new FileInputStream("docker-package.tar");
        ByteString byteString = ByteString.readFrom(f);
        FileMessage fileMessage = FileMessage.newBuilder().setFile(byteString).setPop(poP).build();
        ResourceGroupProto resourceGroupProto = stub.create(fileMessage);
        f.close();

        String id = resourceGroupProto.getVdusList().get(0).getComputeId();
        ResourceIdentifier resourceIdentifier = ResourceIdentifier.newBuilder().setResourceId(id).setPop(poP).build();


        stub.stopContainer(resourceIdentifier);
        stub.startContainer(resourceIdentifier);

        InputStream fis = new FileInputStream("docker-package.tar");
        ByteString bs = ByteString.readFrom(fis);
        RuntimeMessage upload = RuntimeMessage.newBuilder().setResourceId(id).setPop(poP).addProperty("/").setFile(bs).build();
        stub.uploadFile(upload);

        RuntimeMessage runtimeMessage = RuntimeMessage.newBuilder().setResourceId(id).setPop(poP).addProperty("ls /").build();
        StringResponse s = stub.executeCommand(runtimeMessage);
        log.info(s.toString());

        RuntimeMessage download = RuntimeMessage.newBuilder().setResourceId(id).setPop(poP).addProperty("resourcegroup.json").build();
        FileMessage fm = stub.downloadFile(download);
        log.info(fm.getFile().toStringUtf8());

        stub.remove(resourceIdentifier);
    }


}
