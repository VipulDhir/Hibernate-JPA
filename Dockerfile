From openjdk:8
EXPOSE 8877
ADD target/devops-integration.jar devops-integration.jar
ENTRYPOINT ["java" , "-jar" , "devops-automation.jar"]