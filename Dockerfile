FROM maven:3.8.2-openjdk-17 AS build

WORKDIR /usr/src/app

ADD . /usr/src/app
RUN mvn clean package -Dmaven.test.skip=true


FROM openjdk:17-jdk-alpine
COPY --from=build /usr/src/app/application/target/billing-api-application-0.0.1-SNAPSHOT.jar billing-api.jar

ENTRYPOINT ["java", "-jar","billing-api.jar"]

EXPOSE 8181
