package fr.sample.jahia.training.components.validators;

import fr.sample.jahia.training.components.services.beans.GenericContent;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.decorator.validation.JCRNodeValidator;

/**
 * Validate generic content
 *
 * @author tleclere
 */
public class GenericContentValidator implements JCRNodeValidator {
    /**
     * Generic content node
     */
    private final JCRNodeWrapper jcrNodeWrapper;

    public GenericContentValidator(JCRNodeWrapper jcrNodeWrapper) {
        this.jcrNodeWrapper = jcrNodeWrapper;
    }

    /**
     * Contact mail and repeat mail fields must contain the same value
     *
     * @return generic content node
     */
    @SameFieldValue(property1 = GenericContent.PROPERTY_CONTACTMAIL, property2 = GenericContent.PROPERTY_REPEATMAIL)
    public JCRNodeWrapper getJcrNodeWrapper() {
        return jcrNodeWrapper;
    }
}
