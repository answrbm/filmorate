package ansarbektassov.utils;

import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorBuilder {

    public static String buildError(List<FieldError> fieldErrors) {
        StringBuilder errorMessage = new StringBuilder();
        for(FieldError error : fieldErrors) {
            errorMessage.append(error.getField())
                    .append("-").append(error.getDefaultMessage())
                    .append(";");
        }
        return errorMessage.toString();
    }
}
