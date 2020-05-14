package fr.sample.jahia.training.components.validators;

import fr.sample.jahia.training.components.beans.GenericContent;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.decorator.validation.JCRNodeValidator;

import javax.jcr.RepositoryException;

/**
 * Validate generic content
 * Contact mail and repeat mail fields must contain the same value
 *
 * @author tleclere
 */
@SameFieldValue(property1 = GenericContent.PROPERTY_CONTACTMAIL, property2 = GenericContent.PROPERTY_REPEATMAIL)
public class GenericContentValidator implements JCRNodeValidator {
    /**
     * Generic content node
     */
    private final JCRNodeWrapper jcrNodeWrapper;

    public GenericContentValidator(JCRNodeWrapper jcrNodeWrapper) {
        this.jcrNodeWrapper = jcrNodeWrapper;
    }

    public boolean hasProperty(String property) throws RepositoryException {
        return jcrNodeWrapper.hasProperty(property);
    }

    public String getPropertyAsString(String property) {
        return jcrNodeWrapper.getPropertyAsString(property);
    }
}
