package ca.mcgill.ecse321.Mar1HotelSystem.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;

public interface ManagerRepository extends CrudRepository<Manager, String>{
    public Manager findManagerByEmail(String email);
}
