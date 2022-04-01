FROM openjdk:17
ADD target/Docker-Gateway.jar Docker-Gateway.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","Docker-Gateway.jar"]