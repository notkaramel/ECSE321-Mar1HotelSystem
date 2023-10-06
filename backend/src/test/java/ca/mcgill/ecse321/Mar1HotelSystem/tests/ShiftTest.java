package ca.mcgill.ecse321.Mar1HotelSystem.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ShiftRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Shift;

@SpringBootTest
public class ShiftTest {
   
    @Autowired
	private ShiftRepository shiftRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
	@AfterEach
	public void clearDatabase() {
		shiftRepository.deleteAll();
        employeeRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadShift() {
		// Create shift
        clearDatabase();
		String firstName = "Lucas";
		String lastName = "Pacicco";
		String email = "lucaspacicco@gmail.com";
        int phoneNumber = 111333333;
        String password = "abc";
        int hoursWorked = 7;
        Employee employee = new Employee(firstName, lastName, email, phoneNumber, password, hoursWorked);
        employeeRepository.save(employee);
        Date date = new Date();
        int startTime = 1;
        int endTime = 8;
		Shift shift = new Shift(employee, date, startTime, endTime);
		// shift.setEmployee(employee);
        // shift.setDate(date);
        // shift.setStartTime(startTime);
        // shift.setEndTime(endTime);

		// Save shift
		shiftRepository.save(shift);

		// Read shift from database.
        int shiftId = shift.getShiftId();
		shift = shiftRepository.findShiftByShiftId(shiftId);

		// Assert that shift is not null and has correct attributes.
		assertNotNull(shift);
        assertEquals(employee.getEmail(), shift.getEmployee().getEmail());
        assertEquals(date, shift.getDate());
		assertEquals(startTime, shift.getStartTime());
		assertEquals(endTime, shift.getEndTime());
        clearDatabase();

        
	}

}
