package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {
	@Mock
	private ManagerRepository managerDao;

	@Mock
	private GeneralUserRepository generalUserDao;

	@InjectMocks
	private ManagerService managerService;

	private static final String MANAGER_KEY = "joe@gmail.com";

	@BeforeEach
	public void setMockOutput() {
		lenient().when(managerDao.findManagerByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(MANAGER_KEY)) {
				Manager manager = new Manager();
				manager.setEmail(MANAGER_KEY);
				manager.setPassword("Pass");
				return manager;
			} else {
				return null;
			}
		});
		lenient().when(managerDao.findAll()).thenAnswer((invocation) -> {
			List<Manager> managers = new ArrayList<>();
			Manager manager1 = new Manager();
			manager1.setEmail("joey@gmail.com");
			Manager manager2 = new Manager();
			manager2.setEmail("janet@gmail.com");
			managers.add(manager1);
			managers.add(manager2);
			return managers;
		});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(managerDao.save(any(Manager.class))).thenAnswer(returnParameterAsAnswer);
	}

	/*
	 * Create a new Manager successfully
	 */
	@Test
	public void testCreateManager() {
		String firstName = "Joey";
		String lastName = "Doey";
		String email = "joeye@gmail.com";
		int phoneNumber = 1234567891;
		String password = "worked";
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(manager);
		assertEquals(email, manager.getEmail());
	}

	/*
	 * Fail to create manager if a manager is trying to be created with the same
	 * email as another user
	 */
	@Test
	public void testCreateManagerTwice() {
		String error = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = MANAGER_KEY;
		int phoneNumber = 1234567891;
		String password = "worked";
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
			assertNull(manager);
			assertEquals("User with that email already exists!", error);
		}
	}

	/*
	 * Fail to create a manager if all inputs null
	 */
	@Test
	public void testCreateManagerNull() {
		String error = null;
		String firstName = null;
		String lastName = null;
		String email = null;
		int phoneNumber = 0;
		String password = null;
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {

			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("All inputs null!", error);
	}

	/*
	 * Fail to create a manager if first name inputs null
	 */
	@Test
	public void testCreateManagerAllSpace() {
		String error = null;
		String firstName = "";
		String lastName = "";
		String email = "";
		int phoneNumber = 0;
		String password = "";
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {

			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("All fields are empty!", error);
	}

	/*
	 * Fail to create a manager if first name input empty
	 */
	@Test
	public void testCreateManagerFistNameSpace() {
		String error = null;
		String firstName = "";
		String lastName = "Doe";
		String email = MANAGER_KEY;
		int phoneNumber = 1234567891;
		String password = "worked";
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("The first name cannot be empty!", error);
	}

	/*
	 * Fail to create a manager if last name input empty
	 */
	@Test
	public void testCreateManagerLastNameSpace() {
		String error = null;
		String firstName = "Joe";
		String lastName = "";
		String email = MANAGER_KEY;
		int phoneNumber = 1234567891;
		String password = "worked";
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("The last name cannot be empty!", error);
	}

	/*
	 * Fail to create a manager if email input empty
	 */
	@Test
	public void testCreateManagerEmailSpace() {
		String error = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "";
		int phoneNumber = 1234567891;
		String password = "worked";
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("The email cannot be empty!", error);
	}

	/*
	 * Fail to create a manager if password input empty
	 */
	@Test
	public void testCreateManagerPasswordSpace() {
		String error = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = MANAGER_KEY;
		int phoneNumber = 1234567891;
		String password = "";
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("The password cannot be empty!", error);
	}

	/*
	 * Fail to create a manager if email input missing @
	 */
	@Test
	public void testCreateManagerEmailMissingAt() {
		String error = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joegmail.com";
		int phoneNumber = 1234567891;
		String password = "Pass";
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("The email is invalid!", error);
	}

	/*
	 * Fail to create a manager if email input missing dot
	 */
	@Test
	public void testCreateManagerEmailMissingDot() {
		String error = null;
		String firstName = "Joe";
		String lastName = "Doe";
		String email = "joe@gmailcom";
		int phoneNumber = 1234567891;
		String password = "Pass";
		Manager manager = null;
		try {
			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("The email is invalid!", error);
	}

	/*
	 * Get manager from correct email of existing manager successfully
	 */
	@Test
	public void testGetManagerSuccessful() {
		String error = null;
		Manager manager = null;
		try {
			manager = managerService.getManager("joe@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(manager);
		assertEquals("joe@gmail.com", manager.getEmail());

	}

	/*
	 * Fail to get manager because email of non-existing manager
	 */
	@Test
	public void testGetManagerUnsuccessful() {
		String error = null;
		Manager manager = null;
		try {
			manager = managerService.getManager("jane@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("Manager Not Found", error);

	}

	/*
	 * Get list of managers successfully
	 */
	@Test
	public void testGetManagerListSuccessful() {
		String error = null;
		List<Manager> managers = null;
		try {
			managers = managerService.getAllManagers();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(managers);
		assertEquals("joey@gmail.com", managers.get(0).getEmail());
		assertEquals("janet@gmail.com", managers.get(1).getEmail());
	}

	/*
	 * Update manager password from correct email of existing manager and correct
	 * old password successfully
	 */
	@Test
	public void testUpdateManagerThatExist() {
		String error = null;
		Manager manager = null;
		try {
			manager = managerService.updateManagerPassword("joe@gmail.com", "Pass", "Passed");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(manager);
		assertEquals("Passed", manager.getPassword());
	}

	/*
	 * Fail to update manager password from correct email of existing manager but
	 * incorrect old password
	 */
	@Test
	public void testUpdateManagerThatExistOldPasswordIncorrect() {
		String error = null;
		Manager manager = null;
		try {
			manager = managerService.updateManagerPassword("joe@gmail.com", "wrongPass", "Passed");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();

		}
		assertNull(manager);
		assertEquals("Incorrect old password!", error);
	}

	/*
	 * Fail to update manager password of non-existing manager
	 */
	@Test
	public void testUpdateManagerThatDoesNotExist() {
		String error = null;
		Manager manager = null;
		try {
			manager = managerService.updateManagerPassword("Hey@gmail.com", "WhatUp?", "NothingMuchYou?");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("User Not Found", error);
	}

	/*
	 * Delete manager from correct email of existing manager successfully
	 */
	@Test
	public void testDeleteManagerThatExist() {
		String error = null;
		Boolean manager = null;
		try {
			manager = managerService.deleteManager("joe@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(manager);
		assertEquals(true, manager);

	}

	/*
	 * Unsuccessfully delete manager of non-existing manager
	 */
	@Test
	public void testDeleteManagerThatDoesNotExist() {
		String error = null;
		Boolean manager = null;
		try {
			manager = managerService.deleteManager("jane@gmail.com");

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
			assertEquals("User with that email does not exist!", error);
		}
		assertEquals(false, manager);

	}

}
