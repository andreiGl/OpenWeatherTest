# OpenWeatherTest

OpenWeatherTest is a java web-app/demo to retrieve weather data for the specified city from OpenWeatherMap.com. The city name is user-selected on the main page of the web-app.
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

Open a link in a new browser window: 
http://localhost:8080/

In the case of port number conflict, it is possible to change it by editing 
```
OpenWeatherTest/src/resource/application.properties
```
and modifying server.port, by default it is set to 8080:
```
server.port=8080
```
Please do run a 'clean package' after this modification (from the OpenWeatherTest folder):
```
$ mvn clean package
```
## Implementation Details
Weather data is being loaded in json format. OpenWeatherMap provides an optional XML mode for data retrieval, but apparently xml response is missing some of the field values, required in this test (ie. datetime).  

Sunrise and sunset times are converted to local time with correspondent timezone. 

Today's date field is also a local date (for the selected city). As a side-effect of different timezones, some cities dates could be shown as yesterday or tomorrow (in relation to the user's local date/time). 

Temperature values (both C and F) are rounded to only one decimal digit.
