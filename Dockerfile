# Etapa de construcción: compila la aplicación usando Maven
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
# Copia el archivo pom.xml y el directorio src para aprovechar la caché de Maven
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa final: crea la imagen de ejecución
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Copia el JAR generado en la etapa anterior
COPY --from=build /app/target/asesorhub-backoffice-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
