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
`mvn clean package -P build-docker-image` and push them to a Docker registry with
the command `mvn clean package -P push-docker-image`. 

⚠️ **Note:** You need to
set the `docker.username` property in the [pom.xml](pom.xml) file to be able to
push the images to a Docker registry. Also, previously you need to login to the
Docker registry with the command `docker login` :)
- GitHub Actions which will automatically check that tests are passing when trying
to merge a pull request and also will build and push the Docker images when on 
every push to the master branch.