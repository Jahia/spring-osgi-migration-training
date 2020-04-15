package fr.sample.jahia.training.components.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * JAX-RS Resource configuration
 *
 * @author tleclere
 */
public class SampleResourceConfig extends ResourceConfig {
    /**
     * Initializes an instance of this class providing a list of classes to be registered.
     */
    public SampleResourceConfig() {
        super(HelloWorldEndpoint.class);
    }
}
