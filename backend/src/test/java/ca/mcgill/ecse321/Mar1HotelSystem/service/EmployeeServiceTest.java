package ca.mcgill.ecse321.Mar1HotelSystem.service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeDao;

    @InjectMocks
    private EmployeeService employeeService;

    private static final String EMPLOYEE_KEY = "TestEmployee";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(employeeDao.findEmployeeByEmail(anyString())).thenAnswer((invocation) -> {
            if (invocation.getArgument(0).equals(EMPLOYEE_KEY)) {
                Employee employee = new Employee();
                employee.setEmail(EMPLOYEE_KEY);
                return employee;
            } else {
                return null;
            }
        });
        lenient().when(employeeDao.findAll()).thenAnswer((invocation) -> {
            ArrayList<Employee> employees = new ArrayList<>();
            Employee employee = new Employee();
            employee.setEmail(EMPLOYEE_KEY);
            employees.add(employee);
            return employees;
        });
    }

}
