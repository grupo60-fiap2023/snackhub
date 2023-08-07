FROM eclipse-temurin:17.0.5_8-jre-alpine

COPY infrastructure/build/libs/*.jar /opt/app/snackhub.jar
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

CMD java -jar /opt/app/snackhub.jar
