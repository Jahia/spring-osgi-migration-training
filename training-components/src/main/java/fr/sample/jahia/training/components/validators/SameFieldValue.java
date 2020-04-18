package fr.sample.jahia.training.components.validators;

import org.apache.commons.lang.StringUtils;

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
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
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
    class SameFieldValueValidator implements ConstraintValidator<SameFieldValue, GenericContentValidator> {
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
            if (StringUtils.isNotBlank(genericContentValidator.getContactMail())) {
                return StringUtils.isNotBlank(genericContentValidator.getRepeatMail()) && genericContentValidator.getContactMail().equals(genericContentValidator.getRepeatMail());
            }
            return StringUtils.isBlank(genericContentValidator.getRepeatMail());
        }
    }
}
