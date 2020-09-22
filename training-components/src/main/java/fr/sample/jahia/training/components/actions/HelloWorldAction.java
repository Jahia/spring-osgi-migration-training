package fr.sample.jahia.training.components.actions;

import fr.sample.jahia.training.services.HelloService;
import org.jahia.bin.Action;
import org.jahia.bin.ActionResult;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.render.RenderContext;
import org.jahia.services.render.Resource;
import org.jahia.services.render.URLResolver;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Action say Hello world!
 * Action is an OSGI component registered by @see org.jahia.services.templates.TemplatePackageRegistry$ModuleRegistry
 *
 * @author tleclere
 */
@Component(service = Action.class, immediate = true)
public class HelloWorldAction extends Action {
    /**
     * Action name
     * URL Example: <path>.hello.do
     */
    private static final String NAME = "hello";

    /**
     * HelloService reference in shared module training-services
     */
    private HelloService helloService;

    @Reference(service = HelloService.class)
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * On OSGI component activation
     */
    @Activate
    public void onActivate() {
        setName(NAME);
        // action enabled for all users, guest included
        setRequireAuthenticatedUser(false);
        setRequiredMethods("GET,POST");
    }

    /**
     * Call the action
     *
     * @param httpServletRequest
     * @param renderContext
     * @param resource
     * @param jcrSessionWrapper
     * @param map                url parameters
     * @param urlResolver
     * @return blank page
     */
    @Override
    public ActionResult doExecute(HttpServletRequest httpServletRequest, RenderContext renderContext, Resource resource,
                                  JCRSessionWrapper jcrSessionWrapper, Map<String, List<String>> map, URLResolver urlResolver) {
        // log Hello world! in the console
        helloService.sayHelloWorld();
        return ActionResult.OK;
    }
}
