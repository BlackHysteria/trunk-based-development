# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy as base

RUN apt-get update
RUN apt-get -y install git

# set work dir
WORKDIR /app

# copy resources
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

FROM base as build
RUN ./mvnw package

# run
#FROM eclipse-temurin:17-jre-jammy as production
#EXPOSE 8080
#COPY --from=build /app/target/trunk-based-development-*.jar /trunk-based-development.jar
#CMD ["java", "-jar", "/trunk-based-development.jar"]