package ansarbektassov.exception.handlers;

import ansarbektassov.exception.ErrorResponse;
import ansarbektassov.exception.FilmNotCreatedException;
import ansarbektassov.exception.UserNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlePersonNotFoundException(FilmNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlePersonNotFoundException(UserNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
