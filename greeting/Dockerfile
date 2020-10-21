FROM maven:3.6.3-jdk-11-slim AS BUILDER
MAINTAINER mario-dacosta@hotmail.fr
ARG SPRING_ACTIVE_PROFILE
WORKDIR application
COPY . .
RUN ls -l
RUN mvn clean package -pl greeting -amd -DskipTests=true -Dspring.profiles.active=$SPRING_ACTIVE_PROFILE
FROM openjdk:16-jdk-alpine AS RUNNER
COPY --from=BUILDER /application/greeting/target/*.jar /application/greeting.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/application/greeting.jar"]