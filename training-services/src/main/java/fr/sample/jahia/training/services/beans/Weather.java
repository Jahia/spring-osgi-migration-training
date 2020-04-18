package fr.sample.jahia.training.services.beans;

/**
 * Weather object
 * <pre>
 * "weather": {
 *     "icon":"c03d",
 *     "code":"803",
 *     "description":"Broken clouds"
 * }
 * </pre>
 *
 * @author tleclere
 */
public class Weather {
    public static final String ENDPOINT = "https://www.weatherbit.io/static/img/icons/%s.png";

    private String icon;
    private String description;

    public String getIcon() {
        return String.format(ENDPOINT, icon);
    }

    public String getDescription() {
        return description;
    }
}
