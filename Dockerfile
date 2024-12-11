# 이미지 베이스 설정
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정 (애플리케이션 파일이 위치할 곳)
WORKDIR /app

# 환경변수로 프로필 설정 (프로덕션 환경 지정)
ENV SPRING_PROFILES_ACTIVE=prod

# Jar 파일 변수 설정 (Gradle 빌드 결과물 위치)
ARG JAR_FILE=build/libs/*.jar

# Jar 파일 컨테이너 내부로 복사
COPY ${JAR_FILE} app.jar

# 애플리케이션 실행 명령 설정
ENTRYPOINT ["java", "-jar", "app.jar"]