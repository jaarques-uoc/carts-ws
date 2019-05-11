# carts-ws [![Build Status](https://travis-ci.com/jaarques-uoc/carts-ws.svg?branch=master)](https://travis-ci.com/jaarques-uoc/carts-ws)

Command line tools:
* Spring boot:
    * build: `./gradlew build`
    * run: `./gradlew bootRun`
* Docker:
    * build: `docker build --tag=carts-ws .`
    * run: `docker run -p 7004:8080 -t carts-ws`
    * stop: `docker stop $(docker ps -q --filter ancestor=carts-ws)`
    * stop all containers: `docker stop $(docker ps -a -q)`

* Urls:
    * Travis CI history: https://travis-ci.com/jaarques-uoc/carts-ws/
    * Docker image: https://cloud.docker.com/repository/docker/jaarquesuoc/carts-ws
    * Heroku app health-check: https://carts-ws.herokuapp.com/actuator/health
