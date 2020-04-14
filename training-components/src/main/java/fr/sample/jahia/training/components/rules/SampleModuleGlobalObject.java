package fr.sample.jahia.training.components.rules;

import fr.sample.jahia.training.services.HelloService;
import org.jahia.services.content.rules.ModuleGlobalObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Register shared service in global object
 * ModuleGlobalObject is an OSGI component registered by @see org.jahia.services.templates.TemplatePackageRegistry$ModuleRegistry
 *
 * @author tleclere
 */
@Component(service = ModuleGlobalObject.class, immediate = true)
public class SampleModuleGlobalObject extends ModuleGlobalObject {
    /**
     * Register helloService with key helloService
     *
     * @param helloService
     */
    @Reference(service = HelloService.class)
    public void setHelloService(HelloService helloService) {
        getGlobalRulesObject().put("helloService", helloService);
    }
}
