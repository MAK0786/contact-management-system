# Stage 1: Build with Maven and JDK 17
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first for caching
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the jar (skip tests if you want)
RUN mvn clean package -DskipTests

# Stage 2: Run with JRE 17 Alpine
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
