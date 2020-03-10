package ca.glotov.openweathertest.controller;

import ca.glotov.openweathertest.model.CityForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherControllerRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //@Test
    public void testShowForm() throws Exception {
        String resp = this.restTemplate.getForObject("http://localhost:" + port + "/", String.class);
        assertNotNull(resp);
        assertThat(resp, containsString("Select City"));
        assertThat(resp, containsString("Vancouver,CA"));
        assertThat(resp, containsString("London,GB"));
        assertThat(resp, containsString("Hong Kong,CN"));
    }

    //@Test
    public void testCitynameSubmit() throws Exception {
        CityForm cityForm = new CityForm(Arrays.asList("London,GB", "Hong Kong,CN", "Vancouver,CA"));
        cityForm.setCityName("London,GB");

        HttpEntity<CityForm> request = new HttpEntity<>(cityForm);

        String resp = this.restTemplate.postForObject("http://localhost:" + port + "/", request, String.class);
        assertNotNull(resp);
        assertThat(resp, containsString("Select City"));
        assertThat(resp, containsString("Vancouver,CA"));
        assertThat(resp, containsString("London,GB"));
        assertThat(resp, containsString("Hong Kong,CN"));

    }
}