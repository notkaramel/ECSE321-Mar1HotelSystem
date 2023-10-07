package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;

/**
 * The CRUD Repository Interface to store and retrieve all Employee objects.
 * 
 * @author Antoine Phan (@notkaramel)
 */
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    /**
     * Find an Employee object by their unique email address.
     * 
     * @param email
     * @return the Employee with the email address
     */
    public Employee findEmployeeByEmail(String email);
}
