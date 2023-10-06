package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;

/**
 * The CRUD Repository Interface to store and retrieve all Manager objects.
 *
 * @author Antoine Phan (@notkaramel)
 */
public interface ManagerRepository extends CrudRepository<Manager, String> {
    /**
     * Find a Manager object by their email address.
     *
     * @param email
     * @return the Manager with the email address
     */
    public Manager findManagerByEmail(String email);
}
