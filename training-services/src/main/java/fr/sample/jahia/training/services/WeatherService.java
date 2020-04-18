package fr.sample.jahia.training.services;

import fr.sample.jahia.training.services.beans.WeatherContent;
import fr.sample.jahia.training.services.beans.WeatherData;
import org.apache.http.client.methods.HttpGet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Service to call Weather API
 *
 * @author tleclere
 */
@Component(service = WeatherService.class, immediate = true)
public class WeatherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

    private static final String ENDPOINT_POSTALCODE = "https://api.weatherbit.io/v2.0/current?postal_code=%s&country=FR&key=88a8430d0c0b45e6b2d38d3867d933a9&lang=fr";
    private static final String ENDPOINT_CITYID = "https://api.weatherbit.io/v2.0/current?city_id=%s&key=88a8430d0c0b45e6b2d38d3867d933a9&lang=fr";

    private ApiService apiService;

    @Reference(service = ApiService.class)
    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    private WeatherContent getWeatherContent(String endpoint) {
        if (apiService != null) {
            try {
                WeatherData weatherData = apiService.execute(new HttpGet(endpoint), WeatherData.class);
                if (weatherData != null) {
                    return weatherData.getWeatherContent();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }

    public WeatherContent getWeatherByPostalCode(String postalCode) {
        return getWeatherContent(String.format(ENDPOINT_POSTALCODE, postalCode));
    }

    public WeatherContent getWeatherByCityId(String cityId) {
        return getWeatherContent(String.format(ENDPOINT_CITYID, cityId));
    }
}
