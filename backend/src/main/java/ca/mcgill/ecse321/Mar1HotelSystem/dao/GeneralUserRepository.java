package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;

import org.springframework.data.repository.CrudRepository;


import java.util.List;

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

    
    public List<GeneralUser> findAll();
    // @Query(value = "SELECT t FROM GENERALUSER t RIGHT JOIN FETCH t.role where t.role = tester")            
    // Optional<List<GeneralUser>> test(String tester);
}
