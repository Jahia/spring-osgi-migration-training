package fr.sample.jahia.training.services.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * City
 * <pre>
 * {
 *     "city_id":8953360,
 *     "city_name":"Comugne",
 *     "state_code":"20",
 *     "country_code":"IT",
 *     "country_full":"Italy",
 *     "lat":45.78184,
 *     "lon":12.88979
 * }
 * </pre>
 *
 * @author tleclere
 */
public class WeatherCity {
    @JsonProperty("city_id")
    private String cityId;
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("country_code")
    private String countyCode;
    @JsonProperty("country_full")
    private String countyFull;
    private double lat;
    private double lng;

    public String getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public String getCountyFull() {
        return countyFull;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
