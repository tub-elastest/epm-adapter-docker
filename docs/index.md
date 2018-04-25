# Elastest Platform Manager Docker Adapter

The Docker adapter compliant with ElasTest Platform Manager is used to launch docker instances. To describe the docker
instances the EPM and the Docker Adapter use an internal model called a Resource Group. The Resource group describes docker containers
and the networks connecting them. The Resource Group is packaged together with a metadata file which provides specific 
information relevant for the EPM and the Virtual Infrastructure which in this case is Docker. 

The package has to be a **tar** file and has to have the following structure:
```bash
- Metadata.yaml #Simple metadata file that should include the name of the package
- resource-group.json #The docker-compose file
```

This is an example **Metadata** file:
```yaml
name: example-name
type: docker
```

On the specifics of the Resource Group model please refer to this [documentation](https://github.com/elastest/elastest-platform-manager/blob/master/docs/api/definitions.md)

The adapter is implemented as a Spring Boot Application using Java 8 and the Docker and gRPC libraries.

## Launching the adapter

Before launching the adapter you have to compile it using gradle in the following way:

```bash
./gradlew build -x test
```

Then to run it and auto register it to the already running EPM run the following command:

```bash
java -jar build/libs/epm-adapter-docker-0.0.1-SNAPSHOT.jar --register-adapter <epm-ip> <adapter-ip>
```

Bare in mind that the <epm-ip> and <adapter-ip> have to be substituted with the corresponding ips, so that the
auto-registration process is completed.

## Launching the adapter in a docker container

To start the adapter in a docker container run this command:
```bash
docker run -v /var/run/docker.sock:/var/run/docker.sock -p 50053:50053 --expose 50053 -i -t elastest/epm-adapter-docker
```

## Launching the adapter and the EPM with Docker-Compose

If you want to start both the Elastest Platform Manager and the Docker adapter you can run:

```bash
docker-compose up
```

This will create the docker container for both the adapter and the EPM and will also automatically register 
the adapter to the EPM, so you can start using them straight away.

## Usage 

The Docker adapter is built to be able to connect to multiple Docker environments, therefore before using it you need to
register a Docker environment in the EPM as a PoP. One example is to register the local docker environment using the 
following PoP:

```json
{
  "name": "docker-local",
  "interfaceInfo": [
    {
      "key": "type",
      "value": "docker"
    }
  ],
  "interfaceEndpoint": "unix:///var/run/docker.sock"
}
```

To reach a remote Docker environment you have to first set up the environment to be be reachable through HTTP. The 
best way to do this is by first registering the machine as a Worker in EPM and then configuring it for Docker. 
On how to do this please refer to the EPM [documentation](https://github.com/elastest/elastest-platform-manager/blob/master/docs/usage.md).
