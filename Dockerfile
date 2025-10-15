# syntax=docker/dockerfile:1.7
# --- Build stage ---
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY gradlew gradlew.bat /app/
COPY gradle /app/gradle
COPY build.gradle.kts settings.gradle.kts /app/

RUN --mount=type=cache,target=/root/.gradle ./gradlew --version

COPY src /app/src

RUN --mount=type=cache,target=/root/.gradle ./gradlew bootJar -x test

# --- Runtime stage ---
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app

# Separate ENV lines (no inline comments in a multi-line ENV)
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -XX:InitialRAMPercentage=25 -XX:+UseStringDeduplication"
ENV TZ=UTC
ENV SPRING_PROFILES_ACTIVE=default
ENV SERVER_PORT=8082
ENV KAFKA_HOST=localhost:9092
ENV KAFKA_CONSUMER_GROUP_ID=cinemaabyss-events
ENV KAFKA_CONSUMER_AUTO_OFFSET_RESET=earliest
ENV KAFKA_CONSUMER_EVENT_TOPIC_NAME=events

COPY --from=build /app/build/libs/*SNAPSHOT.jar /app/app.jar

EXPOSE 8082

RUN addgroup --system app && adduser --system --ingroup app app
USER app

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
