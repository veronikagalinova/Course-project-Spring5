FROM openjdk:8-alpine

RUN apk update && apk add bash

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY build/libs/*.jar $PROJECT_HOME/tbb.jar

WORKDIR $PROJECT_HOME

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","./tbb.jar"]