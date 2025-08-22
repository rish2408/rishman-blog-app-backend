# Build stage
FROM maven:3.8.6-jdk-21 as builder
COPY . /app
WORKDIR /app
RUN mvn clean package

# Runtime stage
FROM openjdk:21-jdk-slim
COPY --from=builder /app/target/rishman-blog-app-apis-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]