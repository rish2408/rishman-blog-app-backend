# Build stage
FROM maven:3.9.9-eclipse-temurin-21 as builder
COPY . .
RUN mvn clean install -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jdk
COPY --from=builder /target/rishman-blog-app-apis-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]