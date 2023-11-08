package ca.mcgill.ecse321.Mar1HotelSystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Mar1HotelSystemExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Mar1HotelSystemException.class)
    public ResponseEntity<String> handleException(Mar1HotelSystemException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
    
}
