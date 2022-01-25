# Spring Boot with Docker 

Running a Spring Boot based simple REST API inside a Docker container. The databases i.e MySQL and Postgres DB servers are also running inside seperate Docker containers. The containers are linked via same network i.e used communicate amongst using container names. Here we build the Docker images and run corresponding containers using Docker Compose. These images consists for the Boot app, MySQL server, Postgres server and PgAdmin. Two new volumes are created and used to persist the database data.

# Building and running the application

## 1. Build application deployable using Maven
`mvn clean install -Dmaven.test.skip=true`

## 2. Build latest images using Docker
`docker-compose build`

## 3. Starting all services at once
`docker-compose up`

## 4. Stoping all services at once
`docker-compose down`