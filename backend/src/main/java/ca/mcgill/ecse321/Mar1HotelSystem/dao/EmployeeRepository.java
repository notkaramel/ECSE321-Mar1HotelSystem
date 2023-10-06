package ca.mcgill.ecse321.Mar1HotelSystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
    public Employee findEmployeeByEmail(String email);
}
