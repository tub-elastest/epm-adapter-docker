FROM openjdk:alpine
MAINTAINER baeldung.com

# Set Image Labels
ARG GIT_COMMIT=unspecified
LABEL git_commit=$GIT_COMMIT

ARG COMMIT_DATE=unspecified
LABEL commit_date=$COMMIT_DATE

ARG VERSION=unspecified
LABEL version=$VERSION

COPY epm-adapter-docker.jar .

EXPOSE 50053

ENTRYPOINT java -jar epm-adapter-docker.jar
