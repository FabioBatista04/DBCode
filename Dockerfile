FROM openjdk:17
LABEL description="Aplicação Java"


WORKDIR /usr/app


COPY ./build/libs/DBCode-0.0.1-SNAPSHOT.jar /usr/app/

ENV DATABASE_HOST=mysql
ENV DATABASE_USER=root
ENV DATABASE_PASSWORD=root
ENV DATABASE_NAME=DBCode
ENV DATABASE_PORT=3306

CMD ["java", "-jar", "DBCode-0.0.1-SNAPSHOT.jar"]