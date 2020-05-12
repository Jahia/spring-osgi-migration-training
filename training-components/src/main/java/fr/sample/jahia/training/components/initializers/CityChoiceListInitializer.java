package fr.sample.jahia.training.components.initializers;

import fr.sample.jahia.training.services.CityService;
import fr.sample.jahia.training.services.WeatherService;
import fr.sample.jahia.training.services.beans.City;
import fr.sample.jahia.training.services.beans.WeatherContent;
import org.apache.commons.lang.StringUtils;
import org.jahia.services.content.JCRPropertyWrapper;
import org.jahia.services.content.nodetypes.ExtendedPropertyDefinition;
import org.jahia.services.content.nodetypes.initializers.ChoiceListValue;
import org.jahia.services.content.nodetypes.initializers.ModuleChoiceListInitializer;
import org.jahia.services.content.nodetypes.renderer.AbstractChoiceListRenderer;
import org.jahia.services.content.nodetypes.renderer.ModuleChoiceListRenderer;
import org.jahia.services.render.RenderContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * City choicelist initializer for edit mode
 * Display list of french cities
 * ModuleChoiceListInitializer is an OSGI component registered by @see org.jahia.services.templates.TemplatePackageRegistry$ModuleRegistry
 * ModuleChoiceListRenderer is an OSGI component registered by @see org.jahia.services.templates.TemplatePackageRegistry$ModuleRegistry
 *
 * @author tleclere
 */
@Component(service = {ModuleChoiceListInitializer.class, ModuleChoiceListRenderer.class}, immediate = true)
public class CityChoiceListInitializer extends AbstractChoiceListRenderer implements ModuleChoiceListInitializer, ModuleChoiceListRenderer {
    /**
     * Choicelist initializer key
     */
    private static final String KEY = "city";

    /**
     * City service API
     */
    private CityService cityService;

    /**
     * Weather service API
     */
    private WeatherService weatherService;

    /**
     * Do nothing: key is set with constant CityChoiceListInitializer.KEY
     *
     * @param key
     */
    @Override
    public void setKey(String key) {
    }

    /**
     * @return choicelist initializer key
     */
    @Override
    public String getKey() {
        return KEY;
    }

    /**
     * @param cityService city service API
     */
    @Reference(service = CityService.class)
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * @param weatherService weather service API
     */
    @Reference(service = WeatherService.class)
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * @param extendedPropertyDefinition
     * @param param
     * @param values                     existing choicelist values or null
     * @param locale
     * @param context
     * @return list of locales available in JVM
     */
    @Override
    public List<ChoiceListValue> getChoiceListValues(ExtendedPropertyDefinition extendedPropertyDefinition, String param, List<ChoiceListValue> values, Locale locale, Map<String, Object> context) {
        City[] cities;
        if (StringUtils.isNotBlank(param)) {
            cities = cityService.getFrenchCitiesByDepartment(param);
        } else {
            cities = cityService.getFrenchCities();
        }
        if (cities == null || cities.length == 0) {
            return Collections.emptyList();
        }
        return Stream.of(cities).map(city -> new ChoiceListValue(city.getNom(), city.getCode())).collect(Collectors.toList());
    }

    private String render(String code) {
        WeatherContent weather = weatherService.getWeatherByPostalCode(code);
        if (weather == null) {
            return null;
        }
        return weather.getWeather().getIcon();
    }

    @Override
    public String getStringRendering(RenderContext renderContext, JCRPropertyWrapper jcrPropertyWrapper) throws RepositoryException {
        return render(jcrPropertyWrapper.getValue().getString());
    }

    @Override
    public String getStringRendering(Locale locale, ExtendedPropertyDefinition extendedPropertyDefinition, Object propertyValue) throws RepositoryException {
        if (propertyValue != null) {
            return render(propertyValue.toString());
        }
        return render(null);
    }
}
