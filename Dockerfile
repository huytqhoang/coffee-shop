# Use a base image with Java and a specific version
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /

# Copy the Spring Boot application JAR file into the container
COPY target/coffee-shop-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application will listen on (e.g., 8080)
EXPOSE 8080

# Define the command to run your application when the container starts
CMD ["java", "-jar", "app.jar"]
