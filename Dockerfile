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