FROM adoptopenjdk/openjdk11:alpine-jre
LABEL maintainer="Raqeebul Ketan"
WORKDIR /opt/app
ENV PORT 9095
EXPOSE 9095
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]