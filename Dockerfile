# Build stage
FROM gradle:7.6.1-jdk17 as builder
WORKDIR /build

# 그래들 파일들을 먼저 복사
COPY build.gradle settings.gradle /build/
COPY gradle /build/gradle

# 의존성 다운로드
RUN gradle dependencies --no-daemon

# 소스 복사
COPY src /build/src

# 빌드
RUN gradle build --no-daemon

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# 빌드 결과물 복사
COPY --from=builder /build/build/libs/*.jar app.jar

# 컨테이너 실행 시 실행할 명령
ENTRYPOINT ["java","-jar","/app/app.jar"]