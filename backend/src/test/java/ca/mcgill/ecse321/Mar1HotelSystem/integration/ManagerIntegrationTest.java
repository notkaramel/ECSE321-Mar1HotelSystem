package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ManagerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.MultipleManagerDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@TestInstance(Lifecycle.PER_CLASS)

public class ManagerIntegrationTest {

	@Autowired
	private ManagerRepository managerRepo;

	@Autowired
	private TestRestTemplate client;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		managerRepo.deleteAll();
	}

	/*
	 * Test to get error message indicating list of managers is empty because there
	 * are no mnanagers created yet
	 */
	@Test
	@Order(0)
	public void testGetManagersInvalid() {
		ResponseEntity<String> response = client.getForEntity("/managers", String.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("There are no Managers Found", response.getBody());
	}

	/*
	 * Test successfully creating a manager
	 */
	@Test
	@Order(1)
	public void testCreateManagerValid() {
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/create", managerDto, ManagerDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		assertEquals(password, response.getBody().getPassword());

	}

	/*
	 * Test to successfully get a specific manager
	 */
	@Test
	@Order(2)
	public void testGetSpecificManagerValid() {

		ManagerDto user = createManager();

		ResponseEntity<ManagerDto> response = client.getForEntity("/managers/" + "joe@gmail.com", ManagerDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(user.getFirstName(), response.getBody().getFirstName());
		assertEquals(user.getLastName(), response.getBody().getLastName());
		assertEquals(user.getEmail(), response.getBody().getEmail());
		assertEquals(user.getPhoneNumber(), response.getBody().getPhoneNumber());
		assertEquals(user.getPassword(), response.getBody().getPassword());

	}

	/*
	 * Test to unsuccessfully get a manager that does not exist
	 */
	@Test
	@Order(3)
	public void testGetSpecificManagerInvalid() {
		ManagerDto user = createManager();
		ResponseEntity<String> response = client.getForEntity("/managers/" + "hey@gmail.com", String.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Manager Not Found", response.getBody());

	}

	/*
	 * Test to unsucessfully create another manager with same characteristics
	 */
	@Test
	@Order(4)
	public void testCreateSecondManagerInavlid1() {
		ManagerDto user = createManager();
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";

		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<String> response = client.postForEntity("/managers/create", managerDto, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("User with that email already exists!", response.getBody());
	}

	/*
	 * Test to successfully create a second manager
	 */
	@Test
	@Order(5)
	public void testCreateSecondManagerValid() {
		ManagerDto user = createManager();
		String firstName = "Joey";
		String lastName = "Doey";
		String email = "joey@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/create", managerDto, ManagerDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(firstName, response.getBody().getFirstName());
		assertEquals(lastName, response.getBody().getLastName());
		assertEquals(email, response.getBody().getEmail());
		assertEquals(phoneNumber, response.getBody().getPhoneNumber());
		assertEquals(user.getPassword(), response.getBody().getPassword());
	}

	/*
	 * Test to successfully delete a manager
	 */
	@Test
	@Order(6)
	public void testDeleteManagerValid() {
		ManagerDto manager = createManager();
		ResponseEntity<Boolean> response = client.postForEntity("/managers/delete", manager, Boolean.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	/*
	 * Test to unsuccessfully create a manager because a field is empty
	 */
	@Test
	@Order(7)
	public void testCreateManagerInvalid2() {
		String firstName = "";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);

		ResponseEntity<String> response = client.postForEntity("/managers/create", managerDto, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("The first name cannot be empty!", response.getBody());
	}

	/*
	 * Test to successfully get a list of managers
	 */
	@Test
	@Order(8)
	public void testGetAllManagers() {

		ManagerDto manager1 = createManager();
		ManagerDto manager2 = createSecondManager();
		ResponseEntity<MultipleManagerDto> response = client.getForEntity("/managers", MultipleManagerDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response);
		assertEquals(manager1.getFirstName(), response.getBody().getManagerList().get(0).getFirstName());
		assertEquals(manager1.getLastName(), response.getBody().getManagerList().get(0).getLastName());
		assertEquals(manager1.getEmail(), response.getBody().getManagerList().get(0).getEmail());
		assertEquals(manager1.getPhoneNumber(), response.getBody().getManagerList().get(0).getPhoneNumber());
		assertEquals(manager2.getFirstName(), response.getBody().getManagerList().get(1).getFirstName());
		assertEquals(manager2.getLastName(), response.getBody().getManagerList().get(1).getLastName());
		assertEquals(manager2.getEmail(), response.getBody().getManagerList().get(1).getEmail());
		assertEquals(manager2.getPhoneNumber(), response.getBody().getManagerList().get(1).getPhoneNumber());
	}

	/*
	 * Helper function to create a manager
	 */
	public ManagerDto createManager() {
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/create", managerDto, ManagerDto.class);
		return response.getBody();
	}

	/*
	 * Helper Fuction to create a second manager
	 */
	public ManagerDto createSecondManager() {
		String firstName = "Joey";
		String lastName = "Doey";
		String email = "joey@gmail.com";
		long phoneNumber = 1234567891;
		String password = "pass";
		ManagerDto managerDto = new ManagerDto(firstName, lastName, email, phoneNumber, password);
		ResponseEntity<ManagerDto> response = client.postForEntity("/managers/create", managerDto, ManagerDto.class);
		return response.getBody();
	}

}
