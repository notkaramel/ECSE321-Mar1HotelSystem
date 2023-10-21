package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.RequestRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import jakarta.transaction.Transactional;

/**
 * Service class/methods for the Request features
 * 
 * @author Antoine Phan (@notkaramel)
 */
@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    @Transactional
    public Request createRequest(String requestDescription, Booking booking, boolean isFufilled) {
        Request newRequest = new Request();
        newRequest.setDescription(requestDescription);
        newRequest.setBooking(booking);
        newRequest.setIsFufilled(isFufilled);
        requestRepository.save(newRequest);
        return newRequest;
    }

    @Transactional
    public Request updateRequestDescriptionByRequestId(int requestId, String requestDescription) {
        Request request = requestRepository.findRequestByRequestId(requestId);
        request.setDescription(requestDescription);
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
        try {
            requestRepository.delete(request);
        } catch (Exception e) {
            return false;
        }
        requestRepository.delete(request);
        return true;
    }

    @Transactional
    public boolean deleteAllRequest() {
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
