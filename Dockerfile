FROM openjdk:17-alpine
COPY target/order-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]