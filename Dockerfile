# ------------- 빌드 스테이지 -------------
FROM gradle:jdk17 AS build
WORKDIR /app

# gradle 관련 파일 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./

# Workflow에서 환경변수 가져오기
ARG CURRENT_ENV
ENV CURRENT_ENV=$CURRENT_ENV

# gradlew 실행권한 부여
RUN chmod +x ./gradlew

# 소스코드 복사
COPY src ./src

# 애플리케이션 빌드
RUN ./gradlew build -x test

# ------------- 실행 스테이지 -------------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 빌드된 결과물만 가져오기
COPY --from=build /app/build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]