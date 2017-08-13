FROM openjdk:8-jdk-alpine
MAINTAINER Mariusz Szymanski <mariusz.szymanski@sayen.pl>
VOLUME /tmp
ADD target/xml-analysis-api-0.2.0.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]