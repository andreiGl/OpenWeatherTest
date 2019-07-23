package ca.glotov.openweathertest.service;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class WeatherFetcher {

    public JSONObject urlToJson(String url) throws IOException, JSONException {

        InputStream is = new URL(url).openStream();
        JSONObject jsonObject;
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            jsonObject = new JSONObject(jsonText);
        } finally {
            is.close();
        }
        return jsonObject;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
