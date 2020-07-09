FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
ENV PORT 9090
EXPOSE 9090
COPY  /target/spring-reactive-demo-0.0.1-SNAPSHOT.jar /opt/app.jar
WORKDIR /opt
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar