package fr.sample.jahia.training.services;

import fr.sample.jahia.training.services.beans.City;
import fr.sample.jahia.training.services.beans.WeatherCity;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Service to call City API
 *
 * @author tleclere
 */
@Component(service = CityService.class, immediate = true)
public class CityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);

    private static final String ENDPOINT = "https://geo.api.gouv.fr/communes?fields=nom,code,codesPostaux&format=json";
    private static final String ALL_CITIES = "https://www.weatherbit.io/static/exports/cities_all.json.gz";

    private ApiService apiService;

    @Reference(service = ApiService.class)
    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    public WeatherCity[] getCities() {
        if (apiService != null) {
            try {
                WeatherCity[] data = apiService.downloadGzip(ALL_CITIES, WeatherCity[].class);
                if (data != null) {
                    return data;
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return new WeatherCity[0];
    }

    public City[] getFrenchCities() {
        if (apiService != null) {
            try {
                City[] data = apiService.execute(new HttpGet(ENDPOINT), City[].class);
                if (data != null) {
                    return data;
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return new City[0];
    }

    public City[] getFrenchCitiesByDepartment(String department) {
        City[] cities = getFrenchCities();
        if (cities.length == 0) {
            return cities;
        }
        if ("2A".equals(department) || "2B".equals(department)) {
            department = "20";
        }
        final String predicate = department;
        return Stream.of(cities).filter(city -> StringUtils.startsWith(city.getCode(), predicate)).toArray(City[]::new);
    }
}
