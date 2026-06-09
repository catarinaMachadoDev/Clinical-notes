# Stage 1: Build stage - uses full JDK to compile the application
FROM eclipse-temurin:21-jdk-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy Maven configuration and source code
COPY pom.xml .
COPY src ./src
COPY .mvn ./.mvn
COPY mvnw .

# Build the jar, skipping tests (tests run separately in CI)
RUN chmod +x mvnw && ./mvnw package -DskipTests

# Stage 2: Runtime stage - uses lightweight JRE only (no compiler needed)
FROM eclipse-temurin:21-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy only the compiled jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8181

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]