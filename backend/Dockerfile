# Java 21 버전 사용
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Gradle 빌드 도구 복사 (캐싱 활용)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 의존성 다운로드 (실행 속도 향상)
RUN ./gradlew --no-daemon dependencies

# 소스 코드 복사
COPY src src

# 개발 모드로 실행 (bootRun)
CMD ["./gradlew", "bootRun"]