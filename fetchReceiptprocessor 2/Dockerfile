# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add a volume pointing to /tmp to help with caching layers
VOLUME /tmp

# Set the application JAR file name as a build argument
ARG JAR_FILE=target/fetchReceiptprocessor-0.0.1-SNAPSHOT.jar

# Copy the JAR file to the container
COPY ${JAR_FILE} app.jar

# Expose the port that the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
