FROM openjdk:17-jdk-alpine
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080
WORKDIR /app
COPY target/*.jar app.jar
