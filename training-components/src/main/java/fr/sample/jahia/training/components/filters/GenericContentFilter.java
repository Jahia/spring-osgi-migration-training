package fr.sample.jahia.training.components.filters;

import fr.sample.jahia.training.components.services.beans.GenericContent;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.filter.AbstractFilter;
import org.jahia.services.render.filter.RenderChain;
import org.jahia.services.render.filter.RenderFilter;
import org.osgi.service.component.annotations.Component;

/**
 * Run some code before and after fragment is generated
 * RenderFilter is an OSGI component registered by @see org.jahia.services.templates.TemplatePackageRegistry$ModuleRegistry
 *
 * @author tleclere
 */
@Component(service = RenderFilter.class, immediate = true)
public class GenericContentFilter extends AbstractFilter {
    /**
     * HttpSerletRequest.Session attribute
     */
    private static final String MY_SESSION_ATTRIBUTE = "MY_SESSION_ATTRIBUTE";

    /**
     * Placeholder in fragment generated to be replaced with time generation
     */
    private static final String PLACEHOLDER_TIMEGENERATION = "##TIMEGENERATION##";

    /**
     * Start time generation in ms
     */
    private long startTime;

    /**
     * Instanciate the render filter
     */
    public GenericContentFilter() {
        setApplyOnNodeTypes(GenericContent.NODETYPE);
        setApplyOnEditMode(false);
    }

    /**
     * Code before fragment is generated
     * Save in session start time generation
     *
     * @param renderContext
     * @param resource      resource rendered with the JCR node
     * @param chain
     * @return null to continue render chain
     */
    @Override
    public String prepare(RenderContext renderContext, Resource resource, RenderChain chain) {
        startTime = System.currentTimeMillis();
        renderContext.getRequest().getSession().setAttribute(MY_SESSION_ATTRIBUTE, startTime);
        return null;
    }

    /**
     * Code after fragment generated to modify output
     * Inject time generation
     *
     * @param previousOut
     * @param renderContext
     * @param resource
     * @param chain
     * @return new output with string replacements
     * @throws Exception
     */
    @Override
    public String execute(String previousOut, RenderContext renderContext, Resource resource, RenderChain chain) throws Exception {
        String output = super.execute(previousOut, renderContext, resource, chain);
        output = output.replace(PLACEHOLDER_TIMEGENERATION, String.valueOf(System.currentTimeMillis() - startTime));
        return output;
    }
}
