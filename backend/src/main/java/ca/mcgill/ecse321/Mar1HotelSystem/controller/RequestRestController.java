package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.RequestService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RequestRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.RequestResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;


/**
 * The controller that handles /request endpoint requests
 * Required functionalities:
 * - Create a request (POST)
 * - Get all requests (GET)
 * - Get request by ID (GET)
 * - etc.
 * 
 * @author Lucas Paccico @Lucaspac5
 * @author Adam (@Ad2Am2)
 */
@CrossOrigin(origins = "*")
@RestController
public class RequestRestController {
    
    @Autowired
	private RequestService requestService;


    @GetMapping(value = { "/requests", "/requests/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RequestResponseDto>> getAllRequests() {

        List<Request> requests = requestService.getAllRequests();
        List<RequestResponseDto> requestResponseDtoList = new ArrayList<RequestResponseDto>();

        for (Request request : requests) {
            RequestResponseDto requestResponseDto = new RequestResponseDto(request.getRequestId(), request.getDescription(), request.getBooking(), request.getIsFufilled());
            requestResponseDtoList.add(requestResponseDto);
        }


        return new ResponseEntity<List<RequestResponseDto>>(requestResponseDtoList, HttpStatus.OK);
    }

    @DeleteMapping(value = { "/requests/{requestId}", "/requests/{requestId}/" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteRequestById(@PathVariable int requestId) {
        requestService.deleteRequestById(requestId);
    }

    @GetMapping(value = { "/requests/{requestId}", "/requests/{requestId}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RequestResponseDto> getRequestById(@PathVariable int requestId) {
        Request request = requestService.getRequestById(requestId);
        return new ResponseEntity<RequestResponseDto>(new RequestResponseDto(request.getRequestId(), request.getDescription(), request.getBooking(), request.getIsFufilled()), HttpStatus.OK);
    
    }

    @PostMapping(value = { "/request/create" })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RequestResponseDto> createRequest(@RequestBody RequestRequestDto requestRequestDto) {

        String description = requestRequestDto.getDescription();
        boolean isFufilled = requestRequestDto.getIsFufilled();
        int bookingId = requestRequestDto.getBookingId();

        Request request = requestService.createRequest(description, bookingId, isFufilled);

        return new ResponseEntity<RequestResponseDto>(new RequestResponseDto(request.getRequestId(), request.getDescription(), request.getBooking(), request.getIsFufilled()), HttpStatus.CREATED);
    }


    @PutMapping(value = { "/requests/{requestId}", "/requests/{requestId}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RequestResponseDto> updateRequest(@PathVariable int requestId, @RequestBody RequestResponseDto requestDto) {

        String description = requestDto.getDescription();
        boolean isFufilled = requestDto.getIsFufilled();
        Booking booking = requestDto.getBooking();

        requestService.updateRequestDescriptionByRequestId(requestId, description);
        requestService.updateIsFulfilledByRequestId(requestId, isFufilled);
        requestService.updateBookingByRequestId(requestId, booking);

        Request request = requestService.getRequestById(requestId);

        return new ResponseEntity<RequestResponseDto>(new RequestResponseDto(request.getRequestId(), request.getDescription(), request.getBooking(), request.getIsFufilled()), HttpStatus.OK);

    }



}
