package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.GeneralUser;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Manager;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {
    @Mock
    private ManagerRepository managerDao;

    @InjectMocks
    private ManagerService managerService;

    private static final String MANAGER_KEY = "joe@gmail.com";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(managerDao.findManagerByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(MANAGER_KEY)) {
                Manager manager = new Manager();
                manager.setEmail(MANAGER_KEY);
                return manager;
            } else {
                return null;
            }
        });
}

    @Test
	public void testCreateManager() {
		//assertEquals(0, managerService.getAllManagers().size());
        
        String firstName = "Joe";
        String lastName = "Doe";
		String email = MANAGER_KEY;
        int phoneNumber = 123;
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

	@Test
    public void testCreateManagerNull() {
		//assertEquals(0, managerService.getAllManagers().size());
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

    @Test
	public void testCreateManagerAllSpace() {
		//assertEquals(0, managerService.getAllManagers().size());
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

    @Test
	public void testCreateManagerFistNameSpace() {
		//assertEquals(0, managerService.getAllManagers().size());
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

    @Test
	public void testCreateManagerLastNameSpace() {
		//assertEquals(0, managerService.getAllManagers().size());
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

    @Test
	public void testCreateManagerEmailSpace() {
		//assertEquals(0, managerService.getAllManagers().size());
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

	// To check with the rest of the team if we need to test a phone number of 0
//    @Test
//	public void testCreateManagerPhoneNumberSpace() {
//		//assertEquals(0, managerService.getAllManagers().size());
//        String error = null;
//        String firstName = "Joe";
//        String lastName = "Doe";
//		String email = MANAGER_KEY;
//        int phoneNumber = 0;
//        String password = "worked";
//		Manager manager = null;
//		try {
//			manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
//		} catch (IllegalArgumentException e) {
//			// Check that no error occurred
//			error = e.getMessage();
//		}
//		assertNull(manager);
//		assertEquals("Manager phone number cannot be empty!", error);
//	}

    @Test
	public void testCreateManagerPasswordSpace() {
		//assertEquals(0, managerService.getAllManagers().size());
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

    @Test
	public void testCreateManagerEmailMissingAt() {
		//assertEquals(0, managerService.getAllManagers().size());
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

    @Test
	public void testCreateManagerEmailMissingDot() {
		//assertEquals(0, managerService.getAllManagers().size());
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

	@Test
	public void testGetManagerSuccessful() {
		String error = null;
        String firstName = "Joe";
        String lastName = "Doe";
		String email = "joe@gmail.com";
        int phoneNumber = 1234567891;
        String password = "Pass";
		Manager manager = null;
		managerService.createManager(firstName, lastName, email, phoneNumber, password);
		try {
			manager = managerService.getManager("joe@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(manager);
		assertEquals("joe@gmail.com", manager.getEmail());
	}

	@Test
	public void testGetManagerUnSuccessful() {
		//assertEquals(0, managerService.getAllManagers().size());
        String error = null;
        String firstName = "Joe";
        String lastName = "Doe";
		String email = "joe@gmail.com";
        int phoneNumber = 1234567891;
        String password = "Pass";
		Manager manager = null;
		managerService.createManager(firstName, lastName, email, phoneNumber, password);
		try {
			manager = managerService.getManager("jane@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("Manager Not Found", error);
	}

	// @Test
	// public void testGetManagerListSuccessful() {
	// 	String error = null;
    //     String firstName = "Joe";
    //     String lastName = "Doe";
	// 	String email = "joe@gmail.com";
    //     int phoneNumber = 1234567891;
    //     String password = "Pass";
	// 	List<Manager> managerList = null;
	// 	managerService.createManager(firstName, lastName, email, phoneNumber, password);
	// 	 String firstName2 = "Joey";
    //     String lastName2 = "Doe";
	// 	String email2 = "joey@gmail.com";
    //     int phoneNumber2 = 1234567891;
    //     String password2 = "Pass";	
	// 	managerService.createManager(firstName2, lastName2, email2, phoneNumber2, password2);
	// 	try {
	// 		managerList = managerService.getAllManagers();
	// 	} catch (IllegalArgumentException e) {
	// 		// Check that no error occurred
	// 		error = e.getMessage();
	// 	}
	// 	System.out.println(managerList);
	// 	assertNotNull(managerList);
	// 	assertEquals("joe@gmail.com", managerList.get(0).getEmail());
	// 	assertEquals("joey@gmail.com", managerList.get(1).getEmail());
	// }

	// @Test
	// public void testGetManagerListUnSuccessfulNull() {
	// 	//assertEquals(0, managerService.getAllManagers().size());
	// 	String error = null;
	// 	List<Manager> managerList = null;
	// 	try {
	// 		managerList = managerService.getAllManagers();
	// 	} catch (IllegalArgumentException e) {
	// 		// Check that no error occurred
	// 		error = e.getMessage();
	// 	}
	// 	assertNull(managerList);
	// 	assertEquals("Manager Not Found", error);
	// }

	// @Test
	// public void testGetManagerListUnSuccessfulManagerNotInList() {
	// 	//assertEquals(0, managerService.getAllManagers().size());
    //     String error = null;
    //     String firstName = "Joe";
    //     String lastName = "Doe";
	// 	String email = "joe@gmail.com";
    //     int phoneNumber = 1234567891;
    //     String password = "Pass";
	// 	List<Manager> managerList = null;
	// 	managerService.createManager(firstName, lastName, email, phoneNumber, password);
	// 	 String firstName2 = "Joey";
    //     String lastName2 = "Doe";
	// 	String email2 = "joey@gmail.com";
    //     int phoneNumber2 = 1234567891;
    //     String password2 = "Pass";	
	// 	managerService.createManager(firstName2, lastName2, email2, phoneNumber2, password2);
	// 	try {
	// 		managerList = managerService.getAllManagers();
	// 	} catch (IllegalArgumentException e) {
	// 		// Check that no error occurred
	// 		error = e.getMessage();
	// 	}
	// 	assertNull(managerList);
	// 	assertEquals("Manager Not Found", error);
	// }

	@Test
	public void testUpdateManagerThatExist() {
		String error = null;
        String firstName = "Joe";
        String lastName = "Doe";
		String email = "joe@gmail.com";
        int phoneNumber = 1234567891;
		String password = "Pass";
		Manager manager = null;
		managerService.createManager(firstName, lastName, email, phoneNumber, password);
		try {
			manager = managerService.updateManagerPassword(email, "Pass", "Passed");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNotNull(manager);
		assertEquals("Passed", manager.getPassword());
	}

	@Test
	public void testUpdateManagerThatDoesNotExist() {
		String error = null;
        String firstName = "Joe";
        String lastName = "Doe";
		String email = "joe@gmail.com";
        int phoneNumber = 1234567891;
		String password = "Pass";
		Manager manager = null;
		managerService.createManager(firstName, lastName, email, phoneNumber, password);
		try {
			manager = managerService.updateManagerPassword("Hey@gmail.com", "WhatUp?", "NothingMuchYou?");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals("User Not Found", error);
	}

	@Test
	public void testDeleteManagerThatExist() {
		 String error = null;
        String firstName = "Joe";
        String lastName = "Doe";
		String email = "joee@gmail.com";
        int phoneNumber = 1234567891;
        String password = "Pass";
		Boolean manager = null;
		managerService.createManager(firstName, lastName, email, phoneNumber, password);
		try {
			manager = managerService.deleteManager("joee@gmail.com");
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertEquals(true, manager);
	}

    @Test
	public void testGetExistingManager() {
		assertEquals(MANAGER_KEY, managerService.getManager(MANAGER_KEY).getEmail());
	}
	
}
