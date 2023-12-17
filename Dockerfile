FROM openjdk:17-jdk

WORKDIR /app

COPY target/esk-algafood-api.jar /app/esk-algafood-api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "esk-algafood-api.jar"]