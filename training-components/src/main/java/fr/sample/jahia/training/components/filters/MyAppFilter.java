package fr.sample.jahia.training.components.filters;

import org.jahia.modules.securityfilter.JWTService;
import org.jahia.osgi.BundleUtils;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.filter.AbstractFilter;
import org.jahia.services.render.filter.RenderChain;
import org.jahia.services.render.filter.RenderFilter;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Run some code before myapp fragment is generated
 * RenderFilter is an OSGI component registered by @see org.jahia.services.templates.TemplatePackageRegistry$ModuleRegistry
 *
 * @author tleclere
 */
@Component(service = RenderFilter.class, immediate = true)
public class MyAppFilter extends AbstractFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAppFilter.class);

    /**
     * HttpSerletRequest.Session attribute
     */
    private static final String SESSION_ATTRIBUTE_TOKEN = "$TOKEN$";

    /**
     * Filter configuration on bundle activation
     */
    @Activate
    public void onActivate() {
        setApplyOnNodeTypes("tnt:myApp");
        setApplyOnEditMode(false);
    }


    /**
     * Code before fragment is generated
     * Save in session token GraphQL API
     *
     * @param renderContext
     * @param resource      resource rendered with the JCR node
     * @param chain
     * @return null to continue render chain
     */
    @Override
    public String prepare(RenderContext renderContext, Resource resource, RenderChain chain) {
        JWTService jwtService = BundleUtils.getOsgiService(JWTService.class, null);
        try {
            List<String> scopes = new ArrayList<>();
            scopes.add("training");
            String token = jwtService.createToken(Collections.singletonMap("scopes", scopes));
            renderContext.getRequest().getSession().setAttribute(SESSION_ATTRIBUTE_TOKEN, token);
        } catch (RepositoryException e) {
            LOGGER.warn("Unable to generate token", e);
        }
        return null;
    }
}
