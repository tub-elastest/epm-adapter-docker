package elastest.epm.adapter.docker.core;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.PullImageCmd;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.core.command.PullImageResultCallback;
import com.sun.org.apache.bcel.internal.generic.POP;
import elastest.epm.adapter.docker.AdapterException;
import elastest.epm.adapter.docker.DockerCredentials;
import elastest.epm.adapter.docker.LogstashConfig;
import elastest.epm.adapter.docker.Utils;
import elastest.epm.adapter.docker.generated.PoP;
import elastest.epm.adapter.docker.model.Package;
import elastest.epm.adapter.docker.model.ResourceGroup;
import elastest.epm.adapter.docker.model.VDU;
import elastest.epm.adapter.docker.model.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DockerHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    Utils utils = new Utils();

    private DockerClient getDockerClient(PoP poP) {

        String endpoint = poP.getInterfaceEndpoint();
        DockerClientConfig dockerClientConfig =
                DefaultDockerClientConfig.createDefaultConfigBuilder()
                        .withDockerHost(endpoint)
                        .build();
        return DockerClientBuilder.getInstance(dockerClientConfig).build();
    }

    private DockerClient getDockerClientWithCredentials(PoP poP, DockerCredentials dockerCredentials) {

        String endpoint = poP.getInterfaceEndpoint();
        DockerClientConfig dockerClientConfig =
                DefaultDockerClientConfig.createDefaultConfigBuilder()
                        .withDockerHost(endpoint)
                        .withRegistryUrl(dockerCredentials.getRegistry())
                        .withRegistryUsername(dockerCredentials.getUserName())
                        .withRegistryPassword(dockerCredentials.getPassword())
                        .build();
        return DockerClientBuilder.getInstance(dockerClientConfig).build();
    }

    public ResourceGroup deployResourceGroup(ResourceGroup resourceGroup, PoP poP, LogstashConfig logstashConfig, DockerCredentials dockerCredentials) throws AdapterException {
        log.info("Deploying Resource Group: " + resourceGroup);
        int groupId = (int) (Math.random() * 1000000);
        try {
            for (Network net : resourceGroup.getNetworks()) {
                net.setName(resourceGroup.getName() + "_" + net.getName() + "_" + groupId);
                createNetwork(net, poP);
            }
            for (VDU vdu : resourceGroup.getVdus()) {
                vdu.setName(resourceGroup.getName() + "_" + vdu.getName() + "_" + groupId);
                vdu.setNetName(resourceGroup.getName() + "_" + vdu.getNetName() + "_" + groupId);
                deployVdu(vdu, poP, logstashConfig, dockerCredentials);
            }
        } catch (Exception exc) {
            log.error(exc.getMessage(), exc);
            log.error("Rollback allocation of resource group...");
            for (VDU vdu : resourceGroup.getVdus()) {
                if (vdu.getId() != null) {
                    try {
                        terminateVdu(vdu, poP);
                    } catch (Exception e) {
                        log.warn(e.getMessage());
                    }
                }
            }
            for (Network net : resourceGroup.getNetworks()) {
                if (net.getId() != null) {
                    deleteNetwork(net, poP);
                }
            }
            throw exc;
        }
        log.info("Deployed Resource Group: " + "_" + resourceGroup);
        return resourceGroup;
    }

    public void terminatePackage(Package p, PoP poP){
        for (String containerId : p.getComputeIds()) {
            terminateContainer(containerId, poP);
        }
        for (String networkId : p.getNetworkIds()) {
            deleteNetwork(networkId, poP);
        }
    }

    private void createNetwork(Network network, PoP poP) throws AdapterException {
        DockerClient dockerClient = getDockerClient(poP);
        String networkName = network.getName();
        CreateNetworkResponse createNetworkResponse;
        String networkDriver = "bridge";
        String cidr = network.getCidr();
        if(cidr == null){
            cidr = "192.168." + (int) (Math.random() * 255) + ".0/24";
            network.setCidr(cidr);
        }
        String gateway = cidr.substring(0, cidr.lastIndexOf("/") - 1) + "1";
        com.github.dockerjava.api.model.Network.Ipam ipam = new com.github.dockerjava.api.model.Network.Ipam();
        com.github.dockerjava.api.model.Network.Ipam.Config networkConfig = new com.github.dockerjava.api.model.Network.Ipam.Config();
        networkConfig = networkConfig.withSubnet(cidr).withGateway(gateway);
        ipam = ipam.withConfig(networkConfig);
        createNetworkResponse =
                dockerClient
                        .createNetworkCmd()
                        .withName(networkName)
                        .withDriver(networkDriver)
                        .withIpam(ipam)
                        .exec();

        List<com.github.dockerjava.api.model.Network> networks =
                dockerClient.listNetworksCmd().withIdFilter(createNetworkResponse.getId()).exec();
        network.setNetworkId(createNetworkResponse.getId());
        if (networks.isEmpty()) {
            throw new AdapterException("Failed to create network: " + networkName);
        }
        com.github.dockerjava.api.model.Network dockerNetwork = networks.get(0);
        log.debug("Retrieved information of network just created: " + dockerNetwork);
    }

    private void deleteNetwork(Network network, PoP poP) {
        deleteNetwork(network.getNetworkId(), poP);
    }

    private void deleteNetwork(String networkId, PoP poP){
        log.debug("Terminating virtualised network resources: " + networkId);
        DockerClient dockerClient = getDockerClient(poP);
        log.debug("Removing network: " + networkId);
        dockerClient.removeNetworkCmd(networkId).exec();
        log.debug("Removed network: " + networkId);
    }

    private void deployVdu(VDU vdu, PoP poP, LogstashConfig logstashConfig, DockerCredentials dockerCredentials) {
        DockerClient dockerClient = getDockerClient(poP);

        String containerName = vdu.getName();
        String imageId = vdu.getImageName();
        try {
            if (!imageExists(imageId, poP)) {
                log.info("Not found image " + imageId + ". Try to pull...");
                pullImage(imageId, poP, dockerCredentials);
            }
        } catch (AdapterException e) {
            e.printStackTrace();
        }
        LogConfig logConfig = utils.getLogConfig(containerName, vdu.getMetadata(), logstashConfig);
        List<String> environmentVariables =
                utils.getMetaDataValuesOfKey("ENVIRONMENT_VARIABLE", vdu.getMetadata());
        List<PortBinding> portBindings =
                utils.getPortBindingsFromMetadata(vdu.getMetadata());
        List<Volume> volumes = utils.getVolumesFromMetaData(vdu.getMetadata());
        CreateContainerResponse createdContainer = null;
        try {
            log.debug("Creating Container...");
            createdContainer =
                    dockerClient
                            .createContainerCmd(imageId)
                            .withName(containerName)
                            .withLogConfig(logConfig)
                            .withEnv(environmentVariables)
                            .withVolumes(volumes)
                            .withPortBindings(portBindings)
                            .withNetworkMode(
                                    utils.getMetadataValueOfKey(vdu.getMetadata(), "NETWORK", "bridge"))
                            .exec();
            log.debug("Created Container: " + createdContainer);
            log.debug("Starting Container: " + createdContainer.getId());
            dockerClient.startContainerCmd(createdContainer.getId()).exec();
            log.debug("Started Container: " + createdContainer.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        Container deployedContainer = null;
        for (Container container : dockerClient.listContainersCmd().withShowAll(true).exec()) {
            if (container.getId().equals(createdContainer.getId())) {
                deployedContainer = container;
            }
        }
        if(deployedContainer != null){
            vdu.setComputeId(deployedContainer.getId());
            if(deployedContainer.getNetworkSettings()!= null && deployedContainer.getNetworkSettings().getNetworks() != null
                    && deployedContainer.getNetworkSettings().getNetworks().size() > 0)
                vdu.setIp(deployedContainer.getNetworkSettings().getNetworks().entrySet().iterator().next().getValue().getIpAddress());
        }
    }

    private void terminateVdu(VDU vdu, PoP poP){
        terminateContainer(vdu.getComputeId(), poP);
    }

    private void terminateContainer(String containerId, PoP poP) {
        DockerClient dockerClient = getDockerClient(poP);
        log.info("Terminating container " + containerId);
        try {
            log.debug("Stopping container " + containerId);
            dockerClient.stopContainerCmd(containerId).exec();
            log.debug("Stopped container " + containerId);
        } catch (Exception exc) {
            log.error(exc.getMessage(), exc);
        }
        try {
            log.debug("Deleting container " + containerId);
            dockerClient.removeContainerCmd(containerId).exec();
            log.debug("Deleted container " + containerId);
        } catch (Exception exc) {
            log.error(exc.getMessage(), exc);
        }
    }

    private boolean imageExists(String vcImageId, PoP poP) throws AdapterException {
        log.debug("Checking if image " + vcImageId + " exists");
        boolean imageExists = false;
        DockerClient dockerClient = getDockerClient(poP);
        List<Image> availableImages = dockerClient.listImagesCmd().exec();
        for (Image availableImage : availableImages) {
            log.debug("Image under consideration " + availableImage);
            if (availableImage.getId().equals(vcImageId)) {
                log.debug("Found image " + availableImage);
                imageExists = true;
                break;
            }
            if (availableImage.getRepoTags() != null) {
                for (String tag : availableImage.getRepoTags()) {
                    if (tag.equals(vcImageId) || tag.equals(vcImageId + ":latest")) {
                        log.debug("Found image " + availableImage);
                        imageExists = true;
                        break;
                    }
                }
            }
            if (imageExists) {
                break;
            }
        }
        return imageExists;
    }

    private void pullImage( String vcImageId, PoP poP, DockerCredentials dockerCredentials) throws AdapterException {
        log.info("Pull image " + vcImageId);
        DockerClient dockerClient;
        if( dockerCredentials != null){
            dockerClient = getDockerClientWithCredentials(poP, dockerCredentials);
        }
        else{
            dockerClient = getDockerClient(poP);
        }
        try {
            PullImageCmd pullImageCmd = dockerClient.pullImageCmd(vcImageId);
            pullImageCmd.exec(new PullImageResultCallback()).awaitSuccess();
        } catch (Exception e) {
            log.error("Not Found image " + vcImageId + " -> " + e.getMessage());
            throw new AdapterException("Not found image " + vcImageId + " -> " + e.getMessage());
        }
        log.info("Pulled image " + vcImageId);
    }

    public void startContainer(String id, PoP poP) throws AdapterException {
        DockerClient dockerClient = getDockerClient(poP);
        if (!isRunningContainer(id, poP)) {
            log.debug("Starting container {}", id);
            dockerClient.startContainerCmd(id).exec();
        } else {
            log.debug("Container {} is already running", id);
        }
    }

    public void stopContainer(String id, PoP poP) throws AdapterException {
        DockerClient dockerClient = getDockerClient(poP);
        if (isRunningContainer(id, poP)) {
            log.debug("Stopping container {}", id);
            dockerClient.stopContainerCmd(id).exec();
        } else {
            log.debug("Container {} is not running", id);
        }
    }

    public InputStream downloadFileFromInstance(String id, String filepath, PoP poP)
            throws AdapterException {
        DockerClient dockerClient = getDockerClient(poP);
        InputStream inputStream = null;
        if (existsContainer(id, poP)) {
            log.trace("Copying {} from container {}", filepath, id);
            try {
                inputStream = dockerClient.copyArchiveFromContainerCmd(id, filepath).exec();
            } catch (Exception exc) {
                log.error(exc.getMessage(), exc);
                throw new AdapterException(exc.getMessage());
            }
        }
        return inputStream;
    }

    public String executeOnInstance(String id, String command, boolean awaitCompletion, PoP poP)
            throws AdapterException {
        DockerClient dockerClient = getDockerClient(poP);
        String output = null;
        log.trace(
                "Executing command {} in container {} (await completion {})",
                command,
                id,
                awaitCompletion);
        if (existsContainer(id, poP)) {
            ExecCreateCmdResponse exec =
                    dockerClient
                            .execCreateCmd(id)
                            .withCmd(command.split(" "))
                            .withTty(false)
                            .withAttachStdin(true)
                            .withAttachStdout(true)
                            .withAttachStderr(true)
                            .exec();

            log.trace("Command executed. Exec id: {}", exec.getId());
            OutputStream outputStream = new ByteArrayOutputStream();
            try {
                ExecStartResultCallback startResultCallback =
                        dockerClient
                                .execStartCmd(exec.getId())
                                .withDetach(false)
                                .withTty(true)
                                .exec(new ExecStartResultCallback(outputStream, System.err));
                if (awaitCompletion) {
                    startResultCallback.awaitCompletion();
                }
                output = outputStream.toString();
            } catch (InterruptedException e) {
                log.warn("Exception executing command {} on container {}", command, id, e);
            } finally {
                log.trace("Callback terminated. Result: {}", output);
            }
        }
        return output;
    }

    public void uploadFileToInstance(String id, String remotePath, String hostPath, PoP poP)
            throws AdapterException, IOException {
        DockerClient dockerClient = getDockerClient(poP);
        if (existsContainer(id, poP)) {
            log.trace("Copying {} to container {}", remotePath, id);
            dockerClient
                    .copyArchiveToContainerCmd(id)
                    .withRemotePath(remotePath)
                    .withHostResource(hostPath)
                    .exec();
        }
    }

    public void uploadFileToInstance(String id, String remotePath, InputStream is, PoP poP)
            throws AdapterException, IOException {
        DockerClient dockerClient = getDockerClient(poP);
        if (existsContainer(id, poP)) {
            if (is != null) {
                log.debug("Copying {} to container {}", remotePath, id);

                dockerClient
                        .copyArchiveToContainerCmd(id)
                        .withRemotePath(remotePath)
                        .withTarInputStream(is)
                        .exec();
                is.close();
            }
        }
    }

    public boolean existsContainer(String containerId, PoP poP) throws AdapterException {
        DockerClient dockerClient = getDockerClient(poP);
        boolean exists = true;
        try {
            dockerClient.inspectContainerCmd(containerId).exec();
            log.debug("Container {} already exist", containerId);

        } catch (NotFoundException e) {
            log.debug("Container {} does not exist", containerId);
            exists = false;
        }
        return exists;
    }

    public boolean isRunningContainer(String containerId, PoP poP) throws AdapterException {
        DockerClient dockerClient = getDockerClient(poP);
        boolean isRunning = false;
        if (existsContainer(containerId, poP)) {
            isRunning = dockerClient.inspectContainerCmd(containerId).exec().getState().getRunning();
            log.debug("Container {} is running: {}", containerId, isRunning);
        }
        return isRunning;
    }
}
