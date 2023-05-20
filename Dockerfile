FROM openjdk:8-jdk-alpine
MAINTAINER db737h
WORKDIR /app

RUN apk --no-cache add bash iptables curl busybox-extras

RUN addgroup -S spring && adduser -S spring -G spring

RUN apk add --no-cache --virtual .build-deps g++ python3-dev libffi-dev openssl-dev && \
    apk add --no-cache --update python3 && \
    pip3 install --upgrade pip setuptools

RUN mkdir -p /app/etc

ARG DEPENDENCY=src/main/resources
COPY ${DEPENDENCY}/cadi.properties /app/etc/cadi.properties
COPY ${DEPENDENCY}/keyfile /app/keyfile
COPY ${DEPENDENCY}/truststore2018.jks /app/truststore2018.jks

RUN chmod 777 /app/
RUN chmod 777 /app/etc/

ADD target/rm-bis-rest.jar ./rm-bis-rest.jar

LABEL version="1.0" \
      app="RmBisRest" \
      desc="docker for LMS RM BIS spring boot"

EXPOSE 8080:8080

ENTRYPOINT ["java","-Djasypt.encryptor.password=Aa123456","-Dcom.att.ajsc.aaf.cadi.prop.path=/app/etc/cadi.properties","-jar","./rm-bis-rest.jar"]