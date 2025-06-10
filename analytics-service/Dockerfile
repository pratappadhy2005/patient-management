# Equivalent to Maven's eclipse-temurin base
FROM gradle:8.7-jdk21 AS builder

# Set the working directory
WORKDIR /app

# Copy Gradle build files first for caching
COPY build.gradle settings.gradle ./

# Download dependencies (optional, speeds up build caching)
RUN gradle dependencies --no-daemon

# Copy source code
COPY src src

# Build the Spring Boot fat jar
RUN gradle bootJar --no-daemon

# -------- Runtime Stage --------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the jar built in the build stage
COPY --from=builder /app/build/libs/*.jar ./app.jar

# Expose default Spring Boot port
EXPOSE 4002

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]