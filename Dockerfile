# Use a base image with JDK and Maven pre-installed
FROM maven:4.0.0-jdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Ceopy the Maven configuration fils
COPY pom.xml .

# Download the dependencies and build the application
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

# Create a new stage for the final image
FROM openjdk:17-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled artifact from the previous stage
COPY --from=build /app/target/practice-0.0.1-SNAPSHOT.jar ./app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
