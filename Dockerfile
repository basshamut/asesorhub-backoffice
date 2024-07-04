FROM eclipse-temurin:21-jdk-alpine
COPY target/vetsmart-backoffice-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]