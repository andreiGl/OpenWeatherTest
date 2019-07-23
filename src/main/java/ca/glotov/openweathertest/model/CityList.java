package ca.glotov.openweathertest.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CityList {
    private List<String> cityList = null;

    @Autowired
    public void setCityListNames(@Value("${cities.list}") final String citiesStr) {
        cityList = Arrays.asList(citiesStr.split(";"));
    }

    public List<String> getCityList() {
        return cityList;
    }
}
