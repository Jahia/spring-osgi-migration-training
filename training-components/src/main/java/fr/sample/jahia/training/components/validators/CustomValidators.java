package fr.sample.jahia.training.components.validators;

import fr.sample.jahia.training.components.beans.GenericContent;
import org.jahia.services.content.decorator.validation.JCRNodeValidatorDefinition;
import org.osgi.service.component.annotations.Component;

import java.util.Collections;
import java.util.Map;

/**
 * Custom validators
 * JCRNodeValidatorDefinition is an OSGI component registered by @see org.jahia.services.templates.TemplatePackageRegistry$ModuleRegistry
 *
 * @author tleclere
 */
@Component(service = JCRNodeValidatorDefinition.class, immediate = true)
public class CustomValidators extends JCRNodeValidatorDefinition {
    /**
     * Register custom validator for generic content nodetype
     *
     * @return validators list
     * @see fr.sample.jahia.training.components.beans.GenericContent
     * @see fr.sample.jahia.training.components.validators.GenericContentValidator
     */
    @Override
    public Map<String, Class> getValidators() {
        return Collections.singletonMap(GenericContent.NODETYPE, GenericContentValidator.class);
    }
}
