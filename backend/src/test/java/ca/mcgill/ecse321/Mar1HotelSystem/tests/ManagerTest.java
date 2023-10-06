package ca.mcgill.ecse321.Mar1HotelSystem.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;

@SpringBootTest
public class ManagerTest {

    @Autowired
	private ManagerRepository managerRepository;

    @BeforeEach
	@AfterEach
	public void clearDatabase() {
		managerRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadManager() {
		// Create manager
        clearDatabase();
		String firstName = "Lucas";
		String lastName = "Pacicco";
		String email = "lucaspacicco@gmail.com";
        int phoneNumber = 111333333;
        String password = "abc";
		Manager manager = new Manager(firstName, lastName, email, phoneNumber, password);
		manager.setFistName(firstName);
        manager.setLastName(lastName);
        manager.setEmail(email);
        manager.setPhoneNumber(phoneNumber);
        manager.setPassword(password);

		// Save manager
		managerRepository.save(manager);

		// Read manager from database.
		manager = managerRepository.findManagerByEmail(email);

		// Assert that manager is not null and has correct attributes.
		assertNotNull(manager);
        assertEquals(firstName, manager.getFirstName());
        assertEquals(lastName, manager.getLastName());
		assertEquals(email, manager.getEmail());
		assertEquals(phoneNumber, manager.getPhoneNumber());
		assertEquals(password, manager.getPassword());
        clearDatabase();
	}
}

