package ca.mcgill.ecse321.Mar1HotelSystem.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Account;

public interface AccountRepository extends CrudRepository<Account, String>{
    Account findAccountByEmail(String email);
    
}
