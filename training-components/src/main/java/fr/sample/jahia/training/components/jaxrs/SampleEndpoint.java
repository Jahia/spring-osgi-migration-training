package fr.sample.jahia.training.components.jaxrs;

import fr.sample.jahia.training.services.CityService;
import fr.sample.jahia.training.services.HelloService;
import fr.sample.jahia.training.services.WeatherService;
import fr.sample.jahia.training.services.beans.City;
import fr.sample.jahia.training.services.beans.WeatherCity;
import fr.sample.jahia.training.services.beans.WeatherContent;
import org.apache.commons.lang.StringUtils;
import org.jahia.osgi.BundleUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Hello world JAX-RS endpoint
 *
 * @author tleclere
 */
@Path("/sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleEndpoint {
    /**
     * @return Hello {name}!
     */
    @Path("hello/{name}")
    @GET
    public String sayHello(@PathParam("name") String name) {
        return BundleUtils.getOsgiService(HelloService.class, null).getMessage(name);
    }

    /**
     * Get current weather
     *
     * @param cityId     by cityId @see WeatherCity
     * @param postalCode by french postal code
     * @return current weather
     */
    @Path("weather")
    @GET
    public WeatherContent getWeather(@QueryParam("cityId") String cityId, @QueryParam("postalCode") String postalCode) {
        if (StringUtils.isNotBlank(cityId)) {
            return BundleUtils.getOsgiService(WeatherService.class, null).getWeatherByCityId(cityId);
        }
        // if no city id, return weather by french postal code
        return BundleUtils.getOsgiService(WeatherService.class, null).getWeatherByPostalCode(postalCode);
    }

    /**
     * @return french cities provided by gouv.fr API
     */
    @Path("french-cities")
    @GET
    public Response getFrenchCities() {
        City[] cities = BundleUtils.getOsgiService(CityService.class, null).getFrenchCities();

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(30 * 24 * 60 * 60);
        Response.ResponseBuilder responseBuilder = Response.ok(cities);
        responseBuilder.cacheControl(cacheControl);
        return responseBuilder.build();
    }

    /**
     * @return all cities provided by weather API
     */
    @Path("cities")
    @GET
    public Response getCities() {
        WeatherCity[] cities = BundleUtils.getOsgiService(CityService.class, null).getCities();

        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(24 * 60 * 60);
        Response.ResponseBuilder responseBuilder = Response.ok(cities);
        responseBuilder.cacheControl(cacheControl);
        return responseBuilder.build();
    }
}
