# =========================
# 1) Build stage
# =========================
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Gradle 설정 먼저 (Docker 캐시 최적화)
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle* settings.gradle* ./

RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# 소스 복사 후 빌드
COPY src/ src/
RUN ./gradlew build -x test --no-daemon


# =========================
# 2) Runtime stage (최소 용량)
# =========================
FROM gcr.io/distroless/java21-debian12

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]