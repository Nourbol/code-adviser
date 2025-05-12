FROM amazoncorretto:21.0.2-alpine3.18
VOLUME /tmp
RUN gradlew clean build --no-daemon
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
