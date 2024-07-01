FROM gradle:7.5.1-jdk17 AS builder

COPY --chown=gradle:gradle . /home/gradle/project

WORKDIR /home/gradle/project

RUN gradle bootJar

FROM openjdk:17-jdk-slim

VOLUME /tmp

EXPOSE 8100

COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
