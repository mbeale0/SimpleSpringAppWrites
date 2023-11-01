FROM openjdk:11
VOLUME /tmp
EXPOSE 8082

ARG JAR_FILE=target/SimpleSpringWebApp-0.1.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]