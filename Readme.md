# OpenWeatherTest

OpenWeatherTest is a java web-app/demo to retrieve from OpenWeatherMap.com weather data for the specified city. The city is user-selected on the main page of the web-app.
Application is written using Java 1.8 and Spring Boot 2.1.6. 

## Installation

Create an empty folder in the location of your choice. 
Open it in the command shell and execute:

```
$ git clone https://github.com/andreiGl/OpenWeatherTest.git
$ cd OpenWeatherTest
$ mvn test
$ mvn clean package
```

## Usage

```
$ cd target
$ java -jar openweathertest-1.0-SNAPSHOT.war
```

Open web browser with the address: 
http://localhost:8080/
