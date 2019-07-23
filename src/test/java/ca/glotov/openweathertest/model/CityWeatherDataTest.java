package ca.glotov.openweathertest.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CityWeatherDataTest {

    @Test
    public void testWeatherDescription() {
        final CityWeatherData data = new CityWeatherData();
        data.setWeatherDescription("clear sky");

        String expected = "Clear sky";
        String actual = data.getWeatherDescription();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertTrue(Character.isUpperCase(actual.charAt(0)));
        assertThat(actual, is(expected));
    }

    @Test
    public void testTemperatureC() {
        final CityWeatherData data = new CityWeatherData();
        data.setTemperatureC(29.38);

        String expected = "29.4C";
        String actual = data.getTemperatureC();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertThat(actual, is(expected));
    }

    @Test
    public void testTemperatureF() {
        final CityWeatherData data = new CityWeatherData();
        data.setTemperatureF(80.01);

        String expected = "80.0F";
        String actual = data.getTemperatureF();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertThat(actual, is(expected));
    }

    @Test
    public void testSunSetTime() {
        final CityWeatherData data = new CityWeatherData();
        data.setSunSetTime(1563854778, -25200);

        String expected = "9:06pm";
        String actual = data.getSunSetTime();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertThat(actual, is(expected));
    }

    @Test
    public void testSunRiseTime() {
        final CityWeatherData data = new CityWeatherData();
        data.setSunRiseTime(1563798671, -25200);

        String expected = "5:31am";
        String actual = data.getSunRiseTime();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertThat(actual, is(expected));
    }

    @Test
    public void testCurrentDate() {
        final CityWeatherData data = new CityWeatherData();
        data.setCurrentDate(1563838648, -25200);

        String expected = "July 22, 2019";
        String actual = data.getCurrentDate();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertThat(actual, is(expected));
    }
}