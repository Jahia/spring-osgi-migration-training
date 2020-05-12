package fr.sample.jahia.training.services;

import org.apache.commons.lang.StringUtils;
import org.jahia.osgi.BundleUtils;
import org.jahia.services.content.JCRSessionFactory;
import org.jahia.utils.i18n.Messages;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * Service to get hello world message or log it
 *
 * @author tleclere
 */
@Component(service = HelloService.class, immediate = true)
public class HelloService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);
    private static final String DEFAULT_VALUE = "Hello {0}!";
    private static final String DEFAULT_ARG = "world";

    private Bundle bundle;

    @Activate
    public void onActivate(BundleContext bundleContext) {
        this.bundle = bundleContext.getBundle();
    }

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
        Locale locale = JCRSessionFactory.getInstance().getCurrentLocale();
        locale = locale == null ? Locale.ENGLISH : locale;
        return Messages.format(Messages.get(BundleUtils.getModule(this.bundle), "label.hello", locale, DEFAULT_VALUE), StringUtils.isBlank(name) ? DEFAULT_ARG : name);
    }
}
