package ca.glotov.openweathertest.model;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class CityWeatherData {
    private String currentDate;
    private String cityName;
    private String weatherDescription;
    private String temperatureF;
    private String temperatureC;
    private String sunSetTime;
    private String sunRiseTime;

    public CityWeatherData() {
        currentDate = LocalDate.now().toString();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = StringUtils.capitalize(weatherDescription);
    }

    public String getTemperatureF() {
        return temperatureF;
    }

    public String getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(Double temperature) {
        //rounding to 1 decimal digit:
        temperatureC = (Math.round(temperature * 10) / 10.0) + "C";

        //converting from C to F:
        temperature = (9.0/5.0)*temperature + 32;
        //rounding to 1 decimal digit:
        temperatureF = (Math.round(temperature * 10) / 10.0) + "F";
    }

    public void setTemperatureF(Double temperature) {
        //rounding to 1 decimal digit:
        temperatureF = (Math.round(temperature * 10) / 10.0) + "F";

        //converting from F to C:
        temperature = (5.0/9.0)*(temperature - 32);
        //rounding to 1 decimal digit:
        temperatureC = (Math.round(temperature * 10) / 10.0) + "C";
    }

    public String getSunSetTime() {
        return sunSetTime;
    }

    public void setSunSetTime(long sunSetTime, int timezoneOffset) {
        this.sunSetTime = formatTimeFromSeconds(sunSetTime, timezoneOffset);
    }

    public String getSunRiseTime() {
        return sunRiseTime;
    }

    public void setSunRiseTime(long sunRiseTime, int timezoneOffset) {
        this.sunRiseTime = formatTimeFromSeconds(sunRiseTime, timezoneOffset);
    }

    private String formatTimeFromSeconds(long secondsTime, int timezoneOffset) {
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(secondsTime*1000), ZoneId.of("GMT"));
        OffsetDateTime dateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofTotalSeconds(-timezoneOffset));

        LocalTime localTime = LocalDateTime.ofInstant(dateTime.toInstant(), ZoneOffset.UTC).toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mma");
        return localTime.format(formatter).toLowerCase();    //Requirements specify "am/pm" in lower case
    }

    private String formatDateFromSeconds(long secondsTime, int timezoneOffset) {
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(secondsTime*1000), ZoneId.of("GMT"));
        OffsetDateTime dateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofTotalSeconds(-timezoneOffset));

        LocalDate localDate = LocalDateTime.ofInstant(dateTime.toInstant(), ZoneOffset.UTC).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return localDate.format(formatter);
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(long currentDate, int timezoneOffset) {
        this.currentDate = formatDateFromSeconds(currentDate, timezoneOffset);
    }
}