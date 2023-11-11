package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.RequestRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import jakarta.transaction.Transactional;

/**
 * Service class/methods for the Request features
 * 
 * @author Antoine Phan (@notkaramel)
 * @author Adam Corbier (@Ad2Am2)
 */
@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    BookingService bookingService;

    @Transactional
    public Request createRequest(String requestDescription, Booking booking, boolean isFufilled) {
        
        if (requestDescription == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Request description cannot be null!");
        } else if (requestDescription.trim().length() == 0) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Request description cannot be empty!");
        } else if (booking == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Booking cannot be null!");
        }


        Request newRequest = new Request();
        newRequest.setDescription(requestDescription);
        newRequest.setBooking(booking);
        newRequest.setIsFufilled(isFufilled);
        requestRepository.save(newRequest);
        return newRequest;
    }

    @Transactional
    public Request createRequest(String requestDescription, int bookingId, boolean isFufilled) {
        
        // if (requestDescription == null) {
        //     throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Request description cannot be null!");
        // } else if (requestDescription.trim().length() == 0) {
        //     throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Request description cannot be empty!");
        // } 
        
        Booking booking = bookingService.getBookingById(bookingId);


        Request newRequest = new Request();
        newRequest.setDescription(requestDescription);
        newRequest.setBooking(booking);
        newRequest.setIsFufilled(isFufilled);
        requestRepository.save(newRequest);
        return newRequest;
    }

    @Transactional
    public Request updateRequestDescriptionByRequestId(int requestId, String requestDescription) {

        if (requestDescription == null){
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Request description cannot be null!");
        } else if (requestDescription.trim().length() == 0) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Request description cannot be empty!");
        }


        Request request = requestRepository.findRequestByRequestId(requestId);

        if (request == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Request does not exist!");
        }

        request.setDescription(requestDescription);
        requestRepository.save(request);
        return request;
    }

    @Transactional
    public Request updateBookingByRequestId(int requestId, Booking booking) {

        if (booking == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Booking cannot be null!");
        }


        Request request = requestRepository.findRequestByRequestId(requestId);

        if (request == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Request does not exist!");
        }

        request.setBooking(booking);
        requestRepository.save(request);
        return request;
    }

    @Transactional
    public Request updateIsFulfilledByRequestId(int requestId, boolean isFulfilled) {

        Request request = requestRepository.findRequestByRequestId(requestId);

        if (request == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "Request does not exist!");
        }

        request.setIsFufilled(isFulfilled);
        requestRepository.save(request);
        return request;
    }

    @Transactional
    public List<Request> getRequestsByBooking(Booking booking) {
        return ServiceUtils.toList(requestRepository.findRequestsByBooking(booking));
    }

    @Transactional
    public List<Request> getAllRequests() {
        return ServiceUtils.toList(requestRepository.findAll());
    }

    @Transactional
    public boolean deleteRequestById(int requestId) {
        Request request = requestRepository.findRequestByRequestId(requestId);

        if (request == null) {
            String error = "Request with id " + requestId + " does not exist!";
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, error);
        }

        try {
            requestRepository.delete(request);
        } catch (Exception e) {
            return false;
        }
        requestRepository.delete(request);
        return true;
    }


    @Transactional
    public Request getRequestById(int requestId) {
        Request request = requestRepository.findRequestByRequestId(requestId);

        if (request == null) {
            String error = "Request with id " + requestId + " does not exist!";
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, error);
        }

        return request;
    }
    


    @Transactional
    public boolean deleteAllRequests() {
        List<Request> requests = getAllRequests();
        try {
            for (Request request : requests) {
                requestRepository.delete(request);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
