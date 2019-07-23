package ca.glotov.openweathertest.service;

import org.junit.Test;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class WeatherFetcherTest {

    @Test
    public void urlToJson() throws IOException, JSONException {
        WeatherFetcher weatherFetcher = new WeatherFetcher();
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,CA&APPID=c2477e2d2e081bcb6b4751c871afc0b1&units=metric";

        JSONObject actualJson = weatherFetcher.urlToJson(url);
        String actual = actualJson.toString();

        assertNotNull(actualJson);
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertTrue(actual.charAt(0) == '{');
        assertTrue(actual.charAt(actual.length()-1) == '}');
        assertThat(actual, containsString("weather"));
        assertThat(actual, containsString("main"));
        assertThat(actual, containsString("timezone"));
        assertThat(actual, containsString("description"));
        assertThat(actual, containsString("sunrise"));
        assertThat(actual, containsString("sunset"));

    }
}