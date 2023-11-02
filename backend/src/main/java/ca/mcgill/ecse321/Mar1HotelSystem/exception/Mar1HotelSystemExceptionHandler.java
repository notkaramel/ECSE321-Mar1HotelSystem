package ca.mcgill.ecse321.Mar1HotelSystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Mar1HotelSystemExceptionHandler {

    @ExceptionHandler(Mar1HotelSystemException.class)
    public ResponseEntity<String> handleException(Mar1HotelSystemException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
    
}
