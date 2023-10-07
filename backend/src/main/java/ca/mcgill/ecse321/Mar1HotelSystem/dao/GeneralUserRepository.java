package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD Repository Interface to store and retrieve all GeneralUser (Guest)
 * objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface GeneralUserRepository extends CrudRepository<GeneralUser, String> {
    /**
     * Find a GeneralUser/Guest object by their unique email address.
     * 
     * @param email
     * @return the GeneralUser/Guest with the email address
     */
    public GeneralUser findGeneralUserByEmail(String email);
}
