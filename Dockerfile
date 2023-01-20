FROM openjdk:11
ARG JAR_FILE=target/*.jar
WORKDIR /app

COPY ${JAR_FILE} /app/app.jar

RUN mvn clean install
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/app.jar"]