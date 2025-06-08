# Use Maven with JDK 21 for building
FROM maven:3.9.0-jdk-21 AS builder

WORKDIR /app

# Copy source code
COPY . .

# Build the jar without tests
RUN mvn clean package -DskipTests

# Use JDK 21 runtime for running the app
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/contact-management-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
