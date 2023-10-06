package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Booking;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD Repository Interface to store and retrieve all Booking objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    /**
     * Find a Booking object by its bookingId.
     * 
     * @param bookingId
     * @return the corresponding Booking object
     */
    public Booking findBookingByBookingId(int bookingId);
}
