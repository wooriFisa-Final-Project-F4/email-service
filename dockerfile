FROM openjdk:17-jdk
WORKDIR /app
COPY build/libs/email-service-0.0.1-SNAPSHOT.jar /app/email-service.jar
CMD ["java", "-jar", "/app/email-service.jar"]