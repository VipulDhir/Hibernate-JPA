From openjdk:8
EXPOSE 8877
COPY D:/Core_java_training/Hibernate.Jpademo/target/devops-integration.jar devops-integration.jar
ENTRYPOINT ["java" , "-jar" , "devops-automation.jar"]