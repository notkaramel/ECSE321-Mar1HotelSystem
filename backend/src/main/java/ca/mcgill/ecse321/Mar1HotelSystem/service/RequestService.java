package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.RequestRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        return ServiceUtils.toList(requestRepository.findAll());
    }
}
