FROM eclipse-temurin:21-jdk-alpine
EXPOSE 8080
COPY target\airport-reactive-backend.jar airport-reactive-backend-app.jar

# Define the command to run your Spring Boot application when the container starts.
ENTRYPOINT ["java", "-jar", "airport-reactive-backend-app.jar"]
