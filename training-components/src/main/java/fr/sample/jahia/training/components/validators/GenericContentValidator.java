package fr.sample.jahia.training.components.validators;

import fr.sample.jahia.training.components.beans.GenericContent;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.decorator.validation.JCRNodeValidator;

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

    /**
     * Nodetype properties
     */
    private String contactMail;
    private String repeatMail;

    public String getContactMail() {
        return jcrNodeWrapper.getPropertyAsString(GenericContent.PROPERTY_CONTACTMAIL);
    }

    public String getRepeatMail() {
        return jcrNodeWrapper.getPropertyAsString(GenericContent.PROPERTY_REPEATMAIL);
    }
}
