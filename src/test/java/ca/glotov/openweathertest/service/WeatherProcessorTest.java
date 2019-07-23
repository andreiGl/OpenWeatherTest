package ca.glotov.openweathertest.service;

import ca.glotov.openweathertest.model.CityWeatherData;
import org.junit.Test;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class WeatherProcessorTest {

    @Test
    public void testProcessJson() throws IOException, JSONException {



        WeatherProcessor weatherProcessor = new WeatherProcessor();
        weatherProcessor.setOwmApiKey("c2477e2d2e081bcb6b4751c871afc0b1");
        weatherProcessor.setOwmBaseUrl("https://api.openweathermap.org/data/2.5/weather?");
        weatherProcessor.setMetric(true);

        CityWeatherData actual = weatherProcessor.processJson("Vancouver,CA");

        assertNotNull(actual);
        assertNotNull(actual.getCurrentDate());
        assertNotNull(actual.getSunRiseTime());
        assertNotNull(actual.getSunSetTime());
        assertNotNull(actual.getTemperatureC());
        assertNotNull(actual.getTemperatureF());
        assertNotNull(actual.getWeatherDescription());
        assertNotNull(actual.getCityName());

        assertFalse(actual.getCurrentDate().isEmpty());
        assertFalse(actual.getSunRiseTime().isEmpty());
        assertFalse(actual.getSunSetTime().isEmpty());
        assertFalse(actual.getTemperatureC().isEmpty());
        assertFalse(actual.getTemperatureF().isEmpty());
        assertFalse(actual.getWeatherDescription().isEmpty());
        assertFalse(actual.getCityName().isEmpty());
    }
}