package ansarbektassov.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginValidator.class)
public @interface Login {

    String message() default "Login shouldn't have spaces";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
