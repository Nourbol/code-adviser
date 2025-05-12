FROM gradle:8.7.0-jdk21-alpine AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build --no-daemon

FROM amazoncorretto:21.0.2-alpine3.18
VOLUME /tmp
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]