#FROM maven:3.8.2-jdk-11 AS MAVEN_BUILD
#
## copy the pom and src code to the container
#COPY ./ ./
#
## package our application code
#RUN mvn clean package
#
## the second stage of our build will use open jdk 8 on alpine 3.9
#FROM openjdk:11
#
## copy only the artifacts we need from the first stage and discard the rest
#COPY --from=MAVEN_BUILD /blog/target/blog-0.0.1-SNAPSHOT.jar /blog.jar

# set the startup command to execute the jar
#CMD ["java", "-jar", "/blog.jar"]

FROM jenkins/jenkins:lts
USER root
RUN apt-get update -qq
RUN apt-get install -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
RUN apt-key fingerprint 0EBFCD88


RUN   add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu eoan stable\
  $(lsb_release -cs) \
  stable"
RUN apt-get update
RUN apt-get install docker-ce -y

RUN usermod -aG docker jenkins

RUN apt-get update
RUN apt-get install maven -y

RUN mvn -v




#FROM adoptopenjdk:11-jre-hotspot as builder
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} blog-0.0.1-SNAPSHOT.jar
#RUN java -Djarmode=layertools -jar blog-0.0.1-SNAPSHOT.jar extract
#
#FROM adoptopenjdk:11-jre-hotspot
#COPY --from=builder dependencies/ ./
#COPY --from=builder spring-boot-loader/ ./
#COPY --from=builder internal-dependencies/ ./
#COPY --from=builder snapshot-dependencies/ ./
#COPY --from=builder application/ ./
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
