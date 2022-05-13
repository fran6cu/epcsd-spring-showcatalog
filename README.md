# epcsd-spring-Showcatalog


To execute this microservice:


Previous steps:

1.- Download to local machine docker-compose descriptors with git clone command (https://github.com/ppinedar/epcsd-spring) 
2.- Be sure you have docker and docker-compose binaries installed and working
3.- Execute from local path command 'docker-compose up', wait until all containers are up.
    Zookeeper
    Kafka
    Postgresql
    Adminer

4.- Verify docker containers with docker ps


Steps to build this artifact:

1.- Download project repo to the local machine with git clone command
2.- Build maven project with command 'mvn clean install'
3.- Locate JAR packet and execute with java -jar packet-name.jar

ALL this steps can be done using IDE.


Test project with Swagger http://localhost:18082/swagger-ui/index.html#/
A module springdoc-openapi has been included that auto-generates and publishes the microservice definition file in format
JSON/OpenAPI v3 http://localhost:18082/v3/api-docs/ 



Happy testing!!

