# Use an official OpenJDK runtime as the base image with Java 20
FROM openjdk:20-ea-4-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file to the container
COPY target/invygo-1.0.0.jar /app/invygo.jar

# Expose the port that your Spring Boot application will listen on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "invygo.jar"]
