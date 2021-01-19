FROM maven:3.6.3-jdk-11-slim AS BUILDER
MAINTAINER mario-dacosta@hotmail.fr
WORKDIR application
COPY . .
RUN mvn clean package -pl hotel -am -DskipTests=true

FROM openjdk:16-jdk-alpine AS RUNNER
COPY --from=BUILDER /application/hotel/target/*.jar /application/hotel.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/application/hotel.jar"]