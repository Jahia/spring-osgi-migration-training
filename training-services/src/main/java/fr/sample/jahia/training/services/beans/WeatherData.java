package fr.sample.jahia.training.services.beans;

/**
 * Weather data
 *
 * @author tleclere
 */
public class WeatherData {
    private WeatherContent[] data;
    private int count;

    public WeatherContent getWeatherContent() {
        if (count > 0 && data != null && data.length > 0) {
            return data[0];
        }
        return null;
    }
}
