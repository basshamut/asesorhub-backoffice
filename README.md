### Autenticación
Se implementó autenticación básica con Spring Security.

(Solo para pruebas)
- **Username:** `test@test.com`
- **Password:** `bWlDb250cmFzZcOxYTEyMw==` (El password es `miContraseña123` en Base64)

### Construcción y Ejecución

#### Docker

Construir la imagen Docker:
```bash
docker build -t spring-boot-docker .
```

#### Swagger

La documentación de la API está disponible en:
[Swagger UI](http://localhost:8080/swagger-ui.html)

#### RabbitMQ

Para ejecutar RabbitMQ:

1. Descargar la imagen de RabbitMQ con gestión:
    ```bash
    docker pull rabbitmq:3-management
    ```
2. Ejecutar el contenedor de RabbitMQ:
    ```bash
    docker run -d -p 9090:15672 -p 9091:5672 --name rabbitmq rabbitmq:3-management
    ```
3. Acceder al Dashboard de RabbitMQ:
   [RabbitMQ Dashboard](http://localhost:9090)

