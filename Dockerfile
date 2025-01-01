FROM maven:3.8.2-openjdk-17 AS build
WORKDIR /app
ADD . /app

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /app/application/target/billing-api-application-1.0.0.jar billing-api.jar

ENTRYPOINT ["java", "-jar","billing-api.jar"]

EXPOSE 8181
