[![License badge](https://img.shields.io/badge/license-Apache2-orange.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Docker badge](https://img.shields.io/docker/pulls/elastest/epm-adapter-docker.svg)](https://hub.docker.com/r/elastest/epm/Docker-epm-adapter-docker.md)

<!-- Elastest logo -->
[![][ElasTest Logo]][ElasTest]

Copyright © 2017-2019 [ElasTest]. Licensed under [Apache 2.0 License].

elastest/epm-adapter-docker
==============================

What is epm-adapter-docker?
==============================

The docker adapter compliant to the EPM is used to create docker networks and launch docker containers. To describe the virtualized resources, the EPM internal model called Resource Group is used alongside a metadata file.

# Quick reference

-	**Where to get help**:  

	[the ElastTest mailing list][ElasTest Public Mailing List], [the Elastest Slack][ElasTest Slack], or [Stack Overflow][StackOverflow]

-	**Where to file issues**:  

	Issues and bug reports should be posted to the [GitHub ElasTest Bugtracker].

-	**Maintained by**:  

	[the ElasTest community](https://github.com/elastest)

-	**Published image artifact details**:

	(image metadata, transfer size, etc).

-	**Source of this description**:  

	[docs repo's `template/` directory](https://github.com/mpauls/epm-adapter-docker/blob/master/docs/Docker-epm-adapter-docker.md) ([history](https://github.com//mpauls/epm-adapter-docker/commits/master/docs/Docker-epm-adapter-docker.md))

-	**Supported Docker versions**:  

	[the latest release](https://github.com/docker/docker/releases/latest) (down to 17.03.1 on a best-effort basis)

# What's on this image?


The adapter is implemented using java, Spring Boot, Docker and gRPC libraries.


# How to use this image


To start the adapter in a docker container run this command:
```bash
./gradlew clean build -x test
docker run -v /var/run/docker.sock:/var/run/docker.sock -p 50053:50053 --expose 50053 -i -t epm-adapter-docker
```

The package has to be a **tar** file and has to have the following structure:

```bash
- Metadata.yaml #Simple metadata file that should include the name of the package
- resourcegroup.json #The resource group file
```

This is an example **Metadata** file:
```yaml
name: example-name
```

## Dependencies (other containers or tools)


none


## Integration with other containers or tools)


none

[Apache 2.0 License]: http://www.apache.org/licenses/LICENSE-2.0
[ElasTest]: http://elastest.io/
[ElasTest Logo]: http://elastest.io/images/logos_elastest/elastest-logo-gray-small.png
[ElasTest Twitter]: https://twitter.com/elastestio
[GitHub ElasTest Group]: https://github.com/elastest
[GitHub ElasTest Bugtracker]: https://github.com/elastest/bugtracker
[ElasTest Public Mailing List]: https://groups.google.com/forum/#!forum/elastest-users
[StackOverflow]: http://stackoverflow.com/questions/tagged/elastest
[ElasTest Slack]: elastest.slack.com
[installation_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/installation.md
[usage_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/usage.md
[sdk_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/sdks.md
[adapters_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/adapters.md
[api_online]: http://elastest.io/docs/api/epm/
[api_overview]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/api/overview.md
[api_definitions]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/api/definitions.md
[api_paths]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/api/paths.md
[development_guide]: https://github.com/elastest/elastest-platform-manager/blob/master/docs/development.md
