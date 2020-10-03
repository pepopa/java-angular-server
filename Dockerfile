
FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY rest-services/target/*.jar app.jar
ARG MONGO_URI="mongodb+srv://jenkins:W7bfrqdxfJbfkYx@mongodbcluster-vlunq.mongodb.net/react-web?retryWrites=true&w=majority"
ARG MONGO_USER="jenkins"
ARG MONGO_PASS="W7bfrqdxfJbfkYx"
ARG MONGO_DB="react-web"
EXPOSE 8082
RUN echo "Using DB ${MONGO_URI}"
ENTRYPOINT ["java", \
  "-Dspring.data.mongodb.uri=${MONGO_URI}", \
  "-Dspring.data.mongodb.username=${MONGO_USER}",  \
  "-Dspring.data.mongodb.password=${MONGO_PASS}", \
  "-Dspring.data.mongodb.database=${MONGO_DB}", \
  "-Djava.security.egd=file:/dev/./urandom","-jar", \
  "/app.jar"]