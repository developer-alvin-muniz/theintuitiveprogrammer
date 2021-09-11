FROM maven:3.8.2-jdk-11 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN mvn clean package

# the second stage of our build will use open jdk 8 on alpine 3.9
FROM openjdk:11

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /blog/target/blog-0.0.1-SNAPSHOT.jar /blog.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/blog.jar"]
