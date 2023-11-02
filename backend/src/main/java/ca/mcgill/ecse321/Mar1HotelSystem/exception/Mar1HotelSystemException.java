package ca.mcgill.ecse321.Mar1HotelSystem.exception;

import org.springframework.http.HttpStatus;

public class Mar1HotelSystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public Mar1HotelSystemException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}