package ansarbektassov.exception.handlers;

import ansarbektassov.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleFilmNotCreatedException(FilmNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleFilmNotFoundException(FilmNotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleUserNotCreatedException(UserNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
