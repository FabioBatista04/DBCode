FROM openjdk:17

WORKDIR /usr/app

COPY ./build/libs/DBCode-0.0.1-SNAPSHOT.jar /usr/app/

COPY ./build/libs/DBCode-0.0.1-SNAPSHOT.jar /app/

WORKDIR /app

CMD ["java", "-jar", "DBCode-0.0.1-SNAPSHOT.jar"]