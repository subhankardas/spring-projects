# spring-boot-basics
This repository contains basic features and POC implementations using Spring Boot.

* Here we have added the maven failsafe plugin to run the unit tests and integration tests separately. 
* The integration tests are differentiated by adding 'IT' to the starting or end of the file names. You can run the test or verify goals to test either unit tests or unit and integration tests respectively.

## 1. Run the docker container
Go to the spring-boot-projects > docker directory and there execute the following command to run the required docker images

`docker-compose up`

* **Prerequisites** 
  * RabbitMQ
    * Login to management > http://localhost:15672/
    * Credentials user/pass > Create a virtual host **'rabbitmq-vhost'**

## 2. Build the application image file
`mvn clean install -Dmaven.test.skip=true`

## 3. Run unit tests only
`mvn test`

## 4. Run unit tests and integration tests both
`mvn verify`