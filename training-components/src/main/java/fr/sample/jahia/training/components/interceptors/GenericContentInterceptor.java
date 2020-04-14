package fr.sample.jahia.training.components.interceptors;

import fr.sample.jahia.training.components.services.GenericContentService;
import fr.sample.jahia.training.components.services.beans.GenericContent;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.interceptor.BaseInterceptor;
import org.jahia.services.content.nodetypes.ExtendedPropertyDefinition;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.jcr.Value;
import java.util.Collections;

/**
 * Custom interceptor when generic content node is saved
 *
 * @author tleclere
 */
@Component(service = GenericContentInterceptor.class, immediate = true)
public class GenericContentInterceptor extends BaseInterceptor {
    private GenericContentService genericContentService;

    public GenericContentInterceptor() {
        // intercepts when generic content node is saved
        setNodeTypes(Collections.singleton(GenericContent.NODETYPE));
        // intercepts when language property of generic content node is saved
        setPropertyNames(Collections.singleton(GenericContent.PROPERTY_LANGUAGE));
    }

    /**
     * @param genericContentService generic content service OSGI reference
     */
    @Reference(service = GenericContentService.class)
    public void setGenericContentService(GenericContentService genericContentService) {
        this.genericContentService = genericContentService;
    }

    /**
     * Before the value (of the language property) is saved
     *
     * @param node
     * @param name
     * @param definition
     * @param originalValue
     * @return
     * @throws RepositoryException
     */
    @Override
    public Value beforeSetValue(JCRNodeWrapper node, String name, ExtendedPropertyDefinition definition, Value originalValue) throws RepositoryException {
        // update node description
        node = genericContentService.updateDescription(node, originalValue.getString());
        // set the value
        return super.beforeSetValue(node, name, definition, originalValue);
    }
}
