package ca.glotov.openweathertest.model;

import java.util.List;

public class CityForm {
    private String cityName;
    private List<String> cityList = null;

    public CityForm(List<String> cityList) {
        this.cityList = cityList;
    }

    public List<String> getCityList() {
        return cityList;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }
}
