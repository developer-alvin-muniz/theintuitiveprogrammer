FROM openjdk:11
MAINTAINER baeldung.com
RUN pwd
COPY target/*.jar blog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/blog-0.0.1-SNAPSHOT.jar"]
