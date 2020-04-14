package fr.sample.jahia.training.components.services;

import fr.sample.jahia.training.components.services.beans.GenericContent;
import org.jahia.services.content.JCRNodeWrapper;
import org.osgi.service.component.annotations.Component;

import javax.jcr.RepositoryException;

/**
 * Service to deal about generic content
 * GenericContentService is an OSGI component
 *
 * @author tleclere
 */
@Component(service = GenericContentService.class, immediate = true)
public class GenericContentService {
    /**
     * Interceptor sample: update content description when the component is saved
     *
     * @param node
     * @param language
     * @return node updated
     * @throws RepositoryException
     */
    public JCRNodeWrapper updateDescription(JCRNodeWrapper node, String language) throws RepositoryException {
        node.setProperty(GenericContent.PROPERTY_CODE, String.format("Content in %s", language));
        return node;
    }
}
