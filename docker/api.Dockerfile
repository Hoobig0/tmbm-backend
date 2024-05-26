FROM gradle:7.4.2-jdk17 AS builder

WORKDIR /home/gradle/app

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew

RUN ./gradlew build -x test --parallel --continue || true

COPY src src

RUN ./gradlew clean build -x test --stacktrace --info

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /home/gradle/app/build/libs/*.jar app.jar

EXPOSE 8100

ENTRYPOINT ["java", "-jar", "app.jar"]
