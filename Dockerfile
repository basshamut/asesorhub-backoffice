# Etapa de construcción: usamos Eclipse Temurin JDK 21 (Alpine) e instalamos Maven
FROM eclipse-temurin:21-jdk-alpine AS build
# Instalamos Maven desde los repositorios de Alpine
RUN apk add --no-cache maven

WORKDIR /app
# Copiamos el pom y el código fuente para aprovechar la caché de dependencias
COPY pom.xml .
COPY src ./src
# Compilamos omitiendo los tests
RUN mvn clean package -DskipTests

# Etapa final: imagen ligera con JDK 21 para ejecutar la aplicación
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Copiamos el jar generado en la etapa anterior
COPY --from=build /app/target/asesorhub-backoffice-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
