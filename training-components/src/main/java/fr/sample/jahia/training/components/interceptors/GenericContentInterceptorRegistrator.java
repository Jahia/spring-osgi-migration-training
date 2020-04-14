package fr.sample.jahia.training.components.interceptors;

import org.jahia.services.content.JCRStoreService;
import org.jahia.services.content.interceptor.PropertyInterceptorRegistrator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Register interceptor
 * GenericContentInterceptorRegistrator is an OSGI component
 *
 * @author tleclere
 */
@Component(service = GenericContentInterceptorRegistrator.class, immediate = true)
public class GenericContentInterceptorRegistrator extends PropertyInterceptorRegistrator {
    /**
     * Register interceptor
     *
     * @param genericContentInterceptor interceptor
     * @throws Exception
     */
    @Reference(service = GenericContentInterceptor.class, unbind = "onUnbind")
    public void setGenericContentInterceptor(final GenericContentInterceptor genericContentInterceptor) throws Exception {
        // set jcr store service instance
        setJcrStoreService(JCRStoreService.getInstance());
        // register interceptor
        setPropertyInterceptor(genericContentInterceptor);
        super.afterPropertiesSet();
    }

    /**
     * Unregister interceptor
     *
     * @param genericContentInterceptor interceptor
     * @throws Exception
     */
    public void onUnbind(GenericContentInterceptor genericContentInterceptor) throws Exception {
        super.destroy();
    }
}
