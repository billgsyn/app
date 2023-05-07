FROM openjdk:11
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=uat", "-jar", "/app.jar"]

FROM openjdk:11
RUN echo "Asia/Shanghai" > /etc/timezone
COPY ./**/target/*.jar /root/target/run.jar
ENTRYPOINT java -jar /root/target/run.jar

FROM openjdk11-jre-alpine:latest
COPY pc*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


FROM openjdk11-jre-alpine:latest
COPY *.jar app.jar
ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:MaxRAMFraction=2","-XX:+UseContainerSupport","-XX:InitialRAMPercentage=50.0","-XX:MaxRAMPercentage=80.0","-XX:InitialHeapSize=0","-jar","/app.jar"]
