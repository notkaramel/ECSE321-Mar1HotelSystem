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
public class ManagerRepositoryTest {

	// Create manager

	/**
	 * This test is for the Manager class
	 * 
	 * @author Lucas Pacicco (@Lucaspac5)
	 * 
	 */

	@Autowired
	private ManagerRepository managerRepository;

	// Clear database before and after test
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		managerRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadManager() {

		// Create Manager
		clearDatabase();
		String firstName = "John";
		String lastName = "Doe";
		String email = "johndoe@gmail.com";
		int phoneNumber = 111333333;
		String password = "abc";
		Manager manager = new Manager(firstName, lastName, email, phoneNumber, password);

		// Save manager
		managerRepository.save(manager);

		// Read manager from database
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
