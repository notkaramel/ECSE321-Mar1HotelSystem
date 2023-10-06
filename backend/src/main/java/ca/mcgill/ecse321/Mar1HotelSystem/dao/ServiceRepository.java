package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Service;

/**
 * The CRUD Repository Interface to store and retrieve all Service objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface ServiceRepository extends CrudRepository<Service, Integer> {
    /**
     * Find a Service object by its serviceId.
     * 
     * @param serviceId
     * @return the corresponding Service object
     */
    public Service findServiceByServiceId(int serviceId);
}
