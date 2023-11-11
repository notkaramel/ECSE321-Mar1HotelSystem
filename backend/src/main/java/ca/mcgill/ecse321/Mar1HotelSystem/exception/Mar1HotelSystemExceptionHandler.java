package ca.mcgill.ecse321.Mar1HotelSystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Mar1HotelSystemExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Mar1HotelSystemException.class)
    public ResponseEntity<String> handleMar1HotelSystemException(Mar1HotelSystemException e) {
        return new ResponseEntity<String>(e.getMessage(), e.getStatus());
    }
    
}
