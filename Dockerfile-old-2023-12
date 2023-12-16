FROM openjdk

WORKDIR /app

COPY target/algafood-api-0.0.1-SNAPSHOT.jar /app/algafood-api.jar

ENTRYPOINT ["java", "-jar", "algafood-api.jar"]