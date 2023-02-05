# Spring Cloud Skeleton

This is a skeleton project for Spring Cloud applications. It contains a set of
commonly used services, dependencies and configurations. This project uses maven 
as a build tool and every service is a maven module:

* [api-gateway](src/api-gateway/pom.xml): API Gateway service which will be the entry
point for all the requests to the other services.

* [eureka-server](src/eureka-server/pom.xml): Eureka Server service which will be the
registry for all the other services.

* [frontend-react](src/frontend-react/pom.xml): Frontend application with a ReactJS.

* [shared](src/shared/pom.xml): Shared library with common classes and configurations
for all the services.

Other functionalities that are included in this project are:
- [Docker🐋](https://www.docker.com/) support for all the services integrated with
Maven, so you can build your images locally with the command 
`mvn clean package -P build-docker-image -Ddocker.username={your_name}` and push them 
to a Docker registry with the command `mvn clean package -P push-docker-image 
-Ddocker.username={your_name}`. 

- [ELK](https://www.elastic.co/what-is/elk-stack) configuration for all the services
with Logback to send the logs to a Logstash server via TCP. Then you can visualize
them in Kibana (Elasticsearch address is http://172.17.0.2:9200 when you configure
the Kibana server).
  - The ELK services are configured in the [docker-compose.yml](docker-compose.yml)
  - The Logback configuration is in every service's 
  [logback-spring.xml](src/api-gateway/src/main/resources/logback-spring.xml).
  This configuration only sends the logs to the Logstash server when you are running
  the services with profile `prod` and you can send the environment variables 
  `LOGSTASH_HOST` and `LOGSTASH_PORT` to configure the Logstash server address.

- GitHub Actions which will automatically check that tests are passing when trying
to merge a pull request and also will build and push the Docker images when on 
every push to the master branch. ⚠️ **Note:** You need to set the following secrets 
- in the GitHub repository settings:
  - `DISCORD_URL`: URL of the Discord webhook to send the notifications.
  - `DOCKER_USERNAME`: Username of the Docker registry.
  - `DOCKER_HUB_PASSWORD`: Password of the Docker registry.