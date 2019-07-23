package ca.glotov.openweathertest.controller;

import ca.glotov.openweathertest.model.CityWeatherData;
import ca.glotov.openweathertest.model.CityForm;
import ca.glotov.openweathertest.model.CityList;
import ca.glotov.openweathertest.service.WeatherProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class WeatherController {

    @Autowired
    private WeatherProcessor weatherProcessor;

    @Autowired
    private CityList cityList;

    @Value("${error.msg.invalid.city}")
    private String errMsgInvalidCity;

    @Value("${error.msg.unsupported.city}")
    private String errMsgUnsupportedCity;

    @Value("${error.msg.broken.weather.data}")
    private String errMsgBrokenWeatherData;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("home", "cityForm", new CityForm(cityList.getCityList()));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String citynameSubmit(@Valid @ModelAttribute("cityForm") CityForm cityForm, BindingResult result, ModelMap model) {
        if (result.hasErrors() || cityForm.getCityName()==null || cityForm.getCityName().isEmpty()) {
            model.addAttribute("errorMsg", errMsgInvalidCity);
            model.addAttribute("cityForm", new CityForm(cityList.getCityList()));
            return "home";
        }
        CityWeatherData cityWeatherData;
        try {
            cityWeatherData = weatherProcessor.processJson(cityForm.getCityName());
        } catch (IOException e) {
            model.addAttribute("errorMsg", errMsgUnsupportedCity);
            model.addAttribute("cityForm", new CityForm(cityList.getCityList()));
            return "home";
        } catch (JSONException e) {
            model.addAttribute("errorMsg", errMsgBrokenWeatherData);
            model.addAttribute("cityForm", new CityForm(cityList.getCityList()));
            return "home";
        }
        model.addAttribute("cityData", cityWeatherData);
        model.addAttribute("cityDataStr", cityWeatherData.toString());
        model.addAttribute("cityForm", new CityForm(cityList.getCityList()));
        return "home";
    }

}
