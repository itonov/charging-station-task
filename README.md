# Charging Station Task
### Introduction
This project is created as a personal training task.
### Project Support Features
* Users can persist charging station data
* Users can retrieve charging station data by ID, zipcode or geolocation perimeter (in km)
### Installation Guide
* Clone this repository [here](https://github.com/itonov/charging-station-task).
* Run mvn dependency:resolve to install all dependencies
* You need to have MySQL database installed locally. You can adjust DB credentials in application.properties file
### Usage
* Run "docker-compose up" to run MySQL DB as a Docker container
* Run "mvn spring-boot:run" to run the project
* Connect to the API using Postman on port 8080.
### API Endpoints
| HTTP Verbs | Endpoints | Action                                                                                   |
| --- | --- |------------------------------------------------------------------------------------------|
| POST | /stations/create | To create a new charging station data                                                    |
| GET | /stations/:id | To retrieve charging station data by ID                                                  |
| GET | /stations?zipcode=:zipcode | To retrieve charging stations data by Zipcode                                            |
| GET | /stations?latitude=:latitude&longitude=:longitude&perimeter=:perimeter | To retrieve charging stations data by specified perimeter in km around given geolocation |
| GET | /api-docs | To review documentation                                                                  |
### Technologies Used
* [Maven](https://maven.apache.org/) This is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.
* [Spring Boot](https://spring.io/projects/spring-boot) Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
* [MySQL](https://www.mysql.com/) MySQL is an Oracle-backed open source relational database management system (RDBMS) based on Structured Query Language (SQL).
