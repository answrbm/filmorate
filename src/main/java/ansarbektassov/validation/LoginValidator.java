package ansarbektassov.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<Login, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || !s.contains(" ");
    }
}
