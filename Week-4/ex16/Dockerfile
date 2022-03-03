FROM openjdk:17
COPY /target/*.jar /usr/local/lib/spring.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/local/lib/spring.jar"]