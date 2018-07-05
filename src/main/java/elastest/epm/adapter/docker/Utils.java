package elastest.epm.adapter.docker;

import com.github.dockerjava.api.exception.BadRequestException;
import com.github.dockerjava.api.model.*;
import com.google.gson.Gson;
import elastest.epm.adapter.docker.generated.*;
import elastest.epm.adapter.docker.model.*;
import elastest.epm.adapter.docker.model.Network;
import elastest.epm.adapter.docker.model.VDU;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Utils {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    public static ResourceGroup extractResourceGroup(InputStream p) throws ArchiveException, IOException {
        ArchiveInputStream t = new ArchiveStreamFactory().createArchiveInputStream("tar", p);

        TarArchiveEntry entry = (TarArchiveEntry) t.getNextEntry();
        while (entry != null) {
            if (entry.getName().toLowerCase().contains(".json")) {
                Gson gson = new Gson();
                byte[] content = new byte[(int) entry.getSize()];
                t.read(content, 0, content.length);
                return gson.fromJson(new String(content), ResourceGroup.class);
            }
        }

        return null;
    }


    public static DockerCredentials getDockerCredentials(InputStream p) throws ArchiveException, IOException {
        ArchiveInputStream t = new ArchiveStreamFactory().createArchiveInputStream("tar", p);
        DockerCredentials dockerCredential = null;

        Map<String, Object> values = null;
        TarArchiveEntry entry = (TarArchiveEntry) t.getNextEntry();
        while (entry != null) {
            if (entry.getName().toLowerCase().contains("metadata.yaml")) {
                byte[] content = new byte[(int) entry.getSize()];
                t.read(content, 0, content.length);
                Yaml yaml = new Yaml();

                values = (Map<String, Object>) yaml.load(new String(content));
            }
            entry = (TarArchiveEntry) t.getNextEntry();
        }
        t.close();

        if(values != null && values.containsKey("docker_registry") && values.containsKey("docker_username") && values.containsKey("docker_password")){
            dockerCredential = new DockerCredentials();
            dockerCredential.setRegistry(String.valueOf(values.get("docker_registry")));
            dockerCredential.setUserName(String.valueOf(values.get("docker_username")));
            dockerCredential.setPassword(String.valueOf(values.get("docker_password")));
        }

        return dockerCredential;
    }

    public String getMetadataValueOfKey(
            List<KeyValuePair> metadata, String key, String defaultValue) {
        String value = null;
        if (metadata != null)
            for (KeyValuePair keyValuePair : metadata) {
                if (keyValuePair.getKey().equals(key)) {
                    value = keyValuePair.getValue();
                    break;
                }
            }
        if (value == null) {
            log.warn(
                    "Metadata value for key \"" + key + "\" not found. Use default value: " + defaultValue);
            value = defaultValue;
        }
        return value;
    }

    public List<String> getMetaDataValuesOfKey(String key, List<KeyValuePair> metaData) {
        log.debug("Find values in metadata for key: " + key + " -> " + metaData);
        List<String> values = new ArrayList<>();
        for (KeyValuePair keyValuePair : metaData) {
            if (keyValuePair.getKey().equals(key)) {
                values.add(keyValuePair.getValue());
            }
        }
        log.debug("Found values in metadata for key: " + key + " -> " + values);
        return values;
    }

    public List<PortBinding> getPortBindingsFromMetadata(List<KeyValuePair> metaData) {
        log.debug("Find port bindings in metadata -> " + metaData);
        List<PortBinding> portBindings = new ArrayList<>();
        for (KeyValuePair keyValuePair : metaData) {
            if (keyValuePair.getKey().equals("PORT_BINDING")) {
                String[] portSplit = keyValuePair.getValue().split(Pattern.quote(":"));
                if (portSplit.length == 2) {
                    String hostPort = portSplit[0];
                    String exposedPortString = portSplit[1];
                    String[] exposedPortSplit = exposedPortString.split(Pattern.quote("/"));
                    if (exposedPortSplit.length == 2) {
                        Ports.Binding binding = new Ports.Binding("0.0.0.0", hostPort);
                        int remotePort = Integer.parseInt(exposedPortSplit[0]);
                        String protocol = exposedPortSplit[1].toUpperCase();
                        InternetProtocol internetProtocol = InternetProtocol.valueOf(protocol);
                        ExposedPort exposedPort = new ExposedPort(remotePort, internetProtocol);
                        PortBinding portBinding = new PortBinding(binding, exposedPort);
                        portBindings.add(portBinding);
                    } else {
                        throw new BadRequestException("PORT_BINDING has wrong format -> \"8080:8080/tcp\"");
                    }
                } else {
                    throw new BadRequestException("PORT_BINDING has wrong format -> \"8080:8080/tcp\"");
                }
            }
        }

        log.debug("Found port bindings in metadata -> " + portBindings);
        return portBindings;
    }

    public LogConfig getLogConfig(String containerName, List<KeyValuePair> metadata, LogstashConfig logstashConfig) {
        log.info("Creating Log config for " + containerName + ": " + metadata);
        LogConfig logConfig = new LogConfig();
        if (ifMetadataExists("LOGSTASH_ADDRESS", metadata)) {
            logConfig.setType(
                    LogConfig.LoggingType.valueOf(
                            getMetadataValueOfKey(metadata, "LOGSTASH_LOGGING_TYPE", "SYSLOG")));
            HashMap logConfigMap = new HashMap();
            logConfigMap.put(
                    "syslog-address",
                    getMetadataValueOfKey(metadata, "LOGSTASH_ADDRESS", "tcp://localhost:5000"));
            logConfigMap.put("tag", containerName);
            logConfig.setConfig(logConfigMap);
            log.info("Created Log config for " + containerName + ": " + logConfig);
        } else if (logstashConfig.isEnabled()) {
            logConfig.setType(
                    LogConfig.LoggingType.valueOf(
                            getMetadataValueOfKey(metadata, "LOGSTASH_LOGGING_TYPE", "SYSLOG")));
            HashMap logConfigMap = new HashMap();
            logConfigMap.put("syslog-address", logstashConfig.getAddress());
            logConfigMap.put("tag", containerName);
            logConfig.setConfig(logConfigMap);
            log.info("Created Log config for " + containerName + ": " + logConfig);
        } else {
            log.debug("LogStash is neither enabled by default nor enabled via the VDU definition...");
        }
        return logConfig;
    }

    private boolean ifMetadataExists(String key, List<KeyValuePair> metadata) {
        String value = null;
        if (metadata != null)
            for (KeyValuePair keyValuePair : metadata) {
                if (keyValuePair.getKey().equals(key)) {
                    value = keyValuePair.getValue();
                    break;
                }
            }
        if (value == null) {
            log.debug("Metadata value for key \"" + key + "\" not found.");
            return false;
        } else {
            return true;
        }
    }

    public List<Volume> getVolumesFromMetaData(List<KeyValuePair> metaData) {
        log.debug("Find volumes in metadata -> " + metaData);
        List<Volume> volumes = new ArrayList<>();
        for (KeyValuePair keyValuePair : metaData) {
            if (keyValuePair.getKey().equals("VOLUME")) {
                Volume volume = new Volume(keyValuePair.getValue());
                volumes.add(volume);
            }
        }
        log.debug("Found volumes in metadata -> " + volumes);
        return volumes;
    }

    public ResourceGroupProto translate(ResourceGroup rg){

        ArrayList<elastest.epm.adapter.docker.generated.Network> networks = new ArrayList<>();
        ArrayList<elastest.epm.adapter.docker.generated.VDU> vdus = new ArrayList<>();

        for(Network network : rg.getNetworks()){
            elastest.epm.adapter.docker.generated.Network n = elastest.epm.adapter.docker.generated.Network.newBuilder()
                    .setName(network.getName())
                    .setCidr(network.getCidr())
                    .setNetworkId(network.getNetworkId())
                    .setPoPName("docker-local")
                    .build();
            networks.add(n);
        }

        for(VDU vdu: rg.getVdus()){
            elastest.epm.adapter.docker.generated.VDU newVDU = elastest.epm.adapter.docker.generated.VDU.newBuilder()
                    .setName(vdu.getName())
                    .setComputeId(vdu.getComputeId())
                    .setImageName(vdu.getImageName())
                    .setNetName(vdu.getNetName())
                    .setPoPName("docker-local")
                    .setIp(vdu.getIp())
                    .build();
            vdus.add(newVDU);
        }

        ResourceGroupProto resourceGroupProto = ResourceGroupProto.newBuilder().setName(rg.getName()).build();
        for(elastest.epm.adapter.docker.generated.Network n : networks)
            resourceGroupProto = resourceGroupProto.toBuilder().addNetworks(n).build();
        for(elastest.epm.adapter.docker.generated.VDU vdu : vdus)
            resourceGroupProto = resourceGroupProto.toBuilder().addVdus(vdu).build();
        return resourceGroupProto;
    }

    public String register(String epmIp, String adapterIp) throws InterruptedException {
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead

        ManagedChannelBuilder<?> channelBuilder =
                ManagedChannelBuilder.forAddress(epmIp, 50050).usePlaintext(true);
        ManagedChannel channel = channelBuilder.build();
        AdapterHandlerGrpc.AdapterHandlerBlockingStub stub = AdapterHandlerGrpc.newBlockingStub(channel);
        AdapterProto adapterProto = AdapterProto.newBuilder().setEndpoint(adapterIp + ":50053").setType("docker").build();
        for(int i = 0; i < 10; i++){
            try{
                ResourceIdentifier id = stub.registerAdapter(adapterProto);

                HttpPost request = new HttpPost("http://" + epmIp + ":8180/v1/pop");
                StringEntity params =new StringEntity(" {\"name\": \"docker-" + adapterIp + "\", \"interfaceEndpoint\":" + adapterIp + ", " +
                                                        "\"interfaceInfo\":[{\"key\": \"type\",\"value\": \"docker\"}]} ");
                request.addHeader("content-type", "application/json");
                request.setEntity(params);
                HttpResponse response = httpClient.execute(request);

                return id.getResourceId();
            }
            catch (Exception e){
                TimeUnit.SECONDS.sleep(11);
            }
        }
        return null;
    }

    public void unregister(String id, String epmIp){
        ManagedChannelBuilder<?> channelBuilder =
                ManagedChannelBuilder.forAddress(epmIp, 50050).usePlaintext(true);
        ManagedChannel channel = channelBuilder.build();
        AdapterHandlerGrpc.AdapterHandlerBlockingStub stub = AdapterHandlerGrpc.newBlockingStub(channel);
        ResourceIdentifier resourceIdentifier = ResourceIdentifier.newBuilder().setResourceId(id).build();
        stub.deleteAdapter(resourceIdentifier);
    }
}
