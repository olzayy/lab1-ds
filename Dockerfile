FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=target/lab1-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

# Запустись, пж
# wtf
# через postman же доходят запросы