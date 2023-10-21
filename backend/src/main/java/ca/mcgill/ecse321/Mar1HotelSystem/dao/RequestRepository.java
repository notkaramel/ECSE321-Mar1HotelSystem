package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;

/**
 * The CRUD Repository Interface to store and retrieve all Request objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface RequestRepository extends CrudRepository<Request, String> {
    /**
     * Find a Request object by its requestId.
     * 
     * @param requestId
     * @return the corresponding Request object
     */
    public Request findRequestByRequestId(int requestId);

    /**
     * Find the Request by its booking.
     * 
     * @param booking
     * @return the corresponding Request object
     */
    public Iterable<Request> findRequestsByBooking(Booking booking);
}
