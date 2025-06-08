# Stage 1: Build the application using Maven with JDK 21
FROM maven:3.9.0-jdk-21 AS builder

WORKDIR /app

# Copy your pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the jar file without running tests
RUN mvn clean package -DskipTests

# Stage 2: Run the application using a lightweight JDK 21 runtime
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the jar file from the build stage
COPY --from=builder /app/target/contact-management-system-0.0.1-SNAPSHOT.jar app.jar

# Expose port your Spring Boot app listens on
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
