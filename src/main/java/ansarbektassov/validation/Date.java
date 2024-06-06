package ansarbektassov.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface Date {
    String message() default "Date must be valid and after the 28th of December 1895";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
