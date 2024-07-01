FROM openjdk:17

EXPOSE 8080

ADD target/DemoForJpa-0.0.1-SNAPSHOT.jar DemoForJpa-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java" , "-jar" , "/DemoForJpa-0.0.1-SNAPSHOT.jar"]