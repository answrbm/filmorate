package ansarbektassov.validation;

import ansarbektassov.validation.Date;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<Date, LocalDate> {

    @Override
    public boolean isValid(LocalDate s, ConstraintValidatorContext constraintValidatorContext) {
        return s.isAfter(LocalDate.of(1895, 12, 28)) ||
                s.isEqual(LocalDate.of(1895, 12, 28));
    }
}
