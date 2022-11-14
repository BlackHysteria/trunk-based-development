FROM maven:3.6.0-jdk-11-slim AS build
COPY . /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

#
# Package stage
#
FROM openjdk:11.0.7-jdk-slim
COPY --from=build /home/app/target/*.jar /usr/local/lib/trunk-based-development.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/usr/local/lib/trunk-based-development.jar"]

FROM openjdk:11.0.7-jdk-slim
COPY target/trunk-based-development-1.0-SNAPSHOT.jar /trunk-based-development.jar
CMD ["java", "-jar", "/trunk-based-development.jar"]