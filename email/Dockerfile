FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

EXPOSE 8082
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]
