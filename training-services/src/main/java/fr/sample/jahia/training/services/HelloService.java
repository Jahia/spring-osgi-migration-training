package fr.sample.jahia.training.services;

import org.apache.commons.lang.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to get hello world message or log it
 *
 * @author tleclere
 */
@Component(service = HelloService.class, immediate = true)
public class HelloService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    /**
     * Log Hello world! in the console
     */
    public void sayHelloWorld() {
        LOGGER.info("{}", getMessage(null));
    }

    /**
     * @param name name or world if name is blank
     * @return Hello ${name}!
     */
    public String getMessage(String name) {
        return String.format("Hello %s!", StringUtils.isBlank(name) ? "world" : name);
    }
}
