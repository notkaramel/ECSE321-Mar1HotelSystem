package ca.mcgill.ecse321.Mar1HotelSystem.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;

public interface RequestRepository extends CrudRepository<Request, String>{
    public Request findRequestByRequestId(int requestId);
}
