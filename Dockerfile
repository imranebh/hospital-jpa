# Dockerfile for Spring Boot application
# This Dockerfile expects the JAR to be built locally first

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR file
COPY hospital/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8086

# Set the entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
