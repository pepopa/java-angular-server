#FROM openjdk:7
#RUN mkdir -p opt/dynamodb
#WORKDIR /opt/dynamodb
#RUN wget https://s3-us-west-2.amazonaws.com/dynamodb-local/dynamodb_local_latest.tar.gz -q -O - | tar -xz
#EXPOSE 8000
#ENTRYPOINT ["java", "-jar", "DynamoDBLocal.jar"]

FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://127.0.0.1:27017/test","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]