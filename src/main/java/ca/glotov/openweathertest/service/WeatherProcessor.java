package ca.glotov.openweathertest.service;

import ca.glotov.openweathertest.model.CityWeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeatherProcessor {

    @Value("${owm.api.key}")
    private String owmApiKey;

    @Value("${owm.base.url}")
    private String owmBaseUrl;

    @Value("${isMetric}")
    private boolean isMetric;

    public void setOwmApiKey(String owmApiKey) {
        this.owmApiKey = owmApiKey;
    }

    public void setOwmBaseUrl(String owmBaseUrl) {
        this.owmBaseUrl = owmBaseUrl;
    }

    public void setMetric(boolean metric) {
        isMetric = metric;
    }

    public CityWeatherData processJson(String cityName) throws IOException, JSONException {
        WeatherFetcher weatherFetcher = new WeatherFetcher();

        String urlString = owmBaseUrl + "APPID=" + owmApiKey +
                "&q=" + cityName +
                "&units=" + (isMetric ? "metric" : "imperial");

        JSONObject json = weatherFetcher.urlToJson(urlString);

        CityWeatherData cityData = new CityWeatherData();

        cityData.setCityName(json.getString("name"));
        int tzOffset = json.getInt("timezone");
        cityData.setCurrentDate(json.getLong("dt"), tzOffset);

        JSONObject sys = json.getJSONObject("sys");
        cityData.setSunRiseTime(sys.getLong("sunrise"), tzOffset);
        cityData.setSunSetTime(sys.getLong("sunset"), tzOffset);

        JSONObject weather = json.getJSONArray("weather").getJSONObject(0);
        cityData.setWeatherDescription(weather.getString("description"));

        JSONObject main = json.getJSONObject("main");
        Double temp = main.getDouble("temp");
        if (isMetric) {
            cityData.setTemperatureC(temp);
        } else {
            cityData.setTemperatureF(temp);
        }

        return cityData;
    }
}
