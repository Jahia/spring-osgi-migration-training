package fr.sample.jahia.training.services.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Weather content
 *
 * @author tleclere
 */
public class WeatherContent {
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("city_name")
    private String cityName;
    private double temp;

    private Weather weather;

    public Weather getWeather() {
        return weather;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTemperature() {
        return temp;
    }
}
