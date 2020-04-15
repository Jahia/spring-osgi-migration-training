package fr.sample.jahia.training.components.jaxrs;

import fr.sample.jahia.training.services.HelloService;
import org.jahia.osgi.BundleUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Hello world JAX-RS endpoint
 *
 * @author tleclere
 */
@Path("/sample")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldEndpoint {
    /**
     * @return helloService reference in shared module training-services
     */
    public static HelloService getHelloService() {
        return BundleUtils.getOsgiService(HelloService.class, null);
    }

    /**
     * @return Hello world!
     */
    @Path("hello")
    @GET
    public String sayHello() {
        return getHelloService().getMessage(null);
    }
}
