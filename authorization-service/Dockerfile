FROM openjdk:11.0.1
ADD target/authorization-service-1.0.0-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+7","-jar","/application.jar"]
