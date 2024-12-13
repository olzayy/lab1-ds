FROM openjdk:17-jdk-slim as builder
WORKDIR /app
ARG JAR_FILE=target/lab1-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

FROM openjdk:17-jdk-slim
WORKDIR app
ENTRYPOINT ["java","-jar","app.jar"]

#FROM openjdk:17-jdk-slim
#WORKDIR /app
#ARG JAR_FILE=lab1/target/lab1-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]

