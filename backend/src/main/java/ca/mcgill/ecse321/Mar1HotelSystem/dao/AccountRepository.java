package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Account;

/**
 * The CRUD Repository Interface to store and retrieve all Account objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */

public interface AccountRepository extends CrudRepository<Account, String> {
    /**
     * Find an Account object by their email address.
     * 
     * @param email
     * @return the Account with the email address
     */
    public Account findAccountByEmail(String email);
}
