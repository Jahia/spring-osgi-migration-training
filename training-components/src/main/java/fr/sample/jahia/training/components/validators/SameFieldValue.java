package fr.sample.jahia.training.components.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Two fields must contain the same value
 *
 * @author tleclere
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SameFieldValueValidator.class)
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
}
