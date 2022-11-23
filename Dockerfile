FROM azul/zulu-openjdk:11-latest

COPY ./target/*.jar ./reprova.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker" ,"./reprova.jar"]

EXPOSE 8080