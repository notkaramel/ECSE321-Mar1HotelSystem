package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Service;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;

public interface ServiceRepository extends CrudRepository<Service, Request> {
    public Service findServiceByRequest(Request request);
}
