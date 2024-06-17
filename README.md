# Charging Station Task
### Introduction
This RESTful project is created as a personal training task. Main goal is for me to try Java 17 and some Docker configurations.
### Project Support Features
* Users can persist charging station data
* Users can retrieve charging station data by ID, zipcode or geolocation perimeter (in km)
### Installation Guide
* Clone this repository [here](https://github.com/itonov/charging-station-task).
* Run mvn dependency:resolve to install all dependencies
* You need to have MySQL database installed locally or run it in Docker container (1st Usage step). You can adjust DB credentials in application.properties file
### Usage
#### Using docker
* Run "docker-compose build" to build the application Jar
* Run "docker-compose up" to run both MySQL DB and Charging station task API as a Docker containers 
* Connect to the API using Postman on port 8080.
#### Using Maven and Spring run (need MySQL installed and running locally)
* Run "mvn spring-boot:run" to run the project
* Connect to the API using Postman on port 8080.
### API Endpoints
| HTTP Verbs | Endpoints | Action                                                                                   |
| --- | --- |------------------------------------------------------------------------------------------|
| POST | /stations/create | To create a new charging station data                                                    |
| GET | /stations/:id | To retrieve charging station data by ID                                                  |
| GET | /stations?zipcode=:zipcode | To retrieve charging stations data by Zipcode                                            |
| GET | /stations?latitude=:latitude&longitude=:longitude&perimeter=:perimeter | To retrieve charging stations data by specified perimeter in km around given geolocation |
| GET | /api-docs | To review API documentation                                                              |
### Technologies Used
* [Maven](https://maven.apache.org/) This is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.
* [Spring Boot](https://spring.io/projects/spring-boot) Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
* [MySQL](https://www.mysql.com/) MySQL is an Oracle-backed open source relational database management system (RDBMS) based on Structured Query Language (SQL).
* [Docker](https://www.docker.com/) Docker is a set of platform as a service products that use OS-level virtualization to deliver software in packages called containers.
* [H2](https://h2database.com/html/main.html) H2 is a relational database management system written in Java. It can be embedded in Java applications or run in client-server mode.
