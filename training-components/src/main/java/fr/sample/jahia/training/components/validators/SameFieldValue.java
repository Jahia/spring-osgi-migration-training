package fr.sample.jahia.training.components.validators;

import org.jahia.services.content.JCRNodeWrapper;

import javax.jcr.RepositoryException;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Two fields must contain the same value
 *
 * @author tleclere
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SameFieldValue.SameFieldValueValidator.class)
@Documented
public @interface SameFieldValue {
    /**
     * @return default error message
     */
    String message() default "{validation.constaint.samefieldvalue}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return field1
     */
    String property1();

    /**
     * @return field2
     */
    String property2();

    /**
     * Validator implementation
     */
    class SameFieldValueValidator implements ConstraintValidator<SameFieldValue, JCRNodeWrapper> {
        private String property1;
        private String property2;

        @Override
        public void initialize(SameFieldValue sameFieldValue) {
            property1 = sameFieldValue.property1();
            property2 = sameFieldValue.property2();
        }

        /***
         * @param jcrNodeWrapper
         * @param constraintValidatorContext
         * @return true if field1 and field2 contain the same value
         */
        @Override
        public boolean isValid(JCRNodeWrapper jcrNodeWrapper, ConstraintValidatorContext constraintValidatorContext) {
            try {
                return jcrNodeWrapper.hasProperty(property1) && jcrNodeWrapper.hasProperty(property2) && jcrNodeWrapper.getProperty(property1).getValue().equals(jcrNodeWrapper.getProperty(property2).getValue());
            } catch (RepositoryException e) {
                return false;
            }
        }
    }
}
