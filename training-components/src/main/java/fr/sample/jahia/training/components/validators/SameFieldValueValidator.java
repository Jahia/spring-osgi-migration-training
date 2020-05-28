package fr.sample.jahia.training.components.validators;

import org.apache.commons.lang.StringUtils;

import javax.jcr.RepositoryException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator implementation
 */
public class SameFieldValueValidator implements ConstraintValidator<SameFieldValue, GenericContentValidator> {
    private String property1;
    private String property2;

    @Override
    public void initialize(SameFieldValue sameFieldValue) {
        property1 = sameFieldValue.property1();
        property2 = sameFieldValue.property2();
    }

    /***
     * @param genericContentValidator
     * @param constraintValidatorContext
     * @return true if field1 and field2 contain the same value
     */
    @Override
    public boolean isValid(GenericContentValidator genericContentValidator, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (genericContentValidator.hasProperty(property1) && StringUtils.isNotBlank(genericContentValidator.getPropertyAsString(property1))) {
                return genericContentValidator.hasProperty(property2) && genericContentValidator.getPropertyAsString(property1).equals(genericContentValidator.getPropertyAsString(property2));
            }
            return genericContentValidator.hasProperty(property2) && StringUtils.isNotBlank(genericContentValidator.getPropertyAsString(property2));
        } catch (RepositoryException e) {
            return false;
        }
    }
}
