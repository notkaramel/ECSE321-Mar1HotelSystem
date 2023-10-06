package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import org.springframework.data.repository.CrudRepository;

public interface GeneralUserRepository extends CrudRepository<GeneralUser, String> {
    public GeneralUser findGeneralUserByEmail(String email);
}
