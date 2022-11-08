FROM openjdk:11.0.7-jdk-slim
COPY target/trunk-based-development-1.0-SNAPSHOT.jar /trunk-based-development.jar
CMD ["java", "-jar", "/trunk-based-development.jar"]