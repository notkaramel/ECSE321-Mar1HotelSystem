package ca.mcgill.ecse321.Mar1HotelSystem.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.AccountRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.CustomerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.GeneralUserRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ManagerRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;

/**
 * Manager Serivce Tests
 * 
 * @author Lucas Pacicco (@Lucaspac5)
 * 
 */
@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {
    @Mock
    private ManagerRepository managerDao;

    @Mock
    private GeneralUserRepository generalUserDao;

    @Mock
    private EmployeeRepository employeeDao;

    @Mock
    private CustomerRepository customerDao;

    @Mock
    private AccountRepository accountDao;

    @InjectMocks
    private ManagerService managerService;

    private static final String MANAGER_KEY = "joe@gmail.com";

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
        setMockOutput();
        String firstName = "Joey";
        String lastName = "Doey";
        String email = "joeye@gmail.com";
        long phoneNumber = 1234567891;
        String password = "worked";
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {
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
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        String firstName = "Joe";
        String lastName = "Doe";
        String email = MANAGER_KEY;
        long phoneNumber = 1234567891;
        String password = "worked";
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("User with that email already exists!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Fail to create a manager if all inputs null
     */
    @Test
    public void testCreateManagerNull() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        long phoneNumber = 0;
        String password = null;
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {

            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("All inputs null!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Fail to create a manager if first name inputs null
     */
    @Test
    public void testCreateManagerAllSpace() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        String firstName = "";
        String lastName = "";
        String email = "";
        long phoneNumber = 0;
        String password = "";
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {

            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("All fields are empty!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Fail to create a manager if first name input empty
     */
    @Test
    public void testCreateManagerFirstNameSpace() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        String firstName = "";
        String lastName = "Doe";
        String email = MANAGER_KEY;
        long phoneNumber = 1234567891;
        String password = "worked";
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("The first name cannot be empty!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Fail to create a manager if last name input empty
     */
    @Test
    public void testCreateManagerLastNameSpace() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        String firstName = "Joe";
        String lastName = "";
        String email = MANAGER_KEY;
        long phoneNumber = 1234567891;
        String password = "worked";
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("The last name cannot be empty!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Fail to create a manager if email input empty
     */
    @Test
    public void testCreateManagerEmailSpace() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        String firstName = "Joe";
        String lastName = "Doe";
        String email = "";
        long phoneNumber = 1234567891;
        String password = "worked";
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("The email cannot be empty!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Fail to create a manager if password input empty
     */
    @Test
    public void testCreateManagerPasswordSpace() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        String firstName = "Joe";
        String lastName = "Doe";
        String email = MANAGER_KEY;
        long phoneNumber = 1234567891;
        String password = "";
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("The password cannot be empty!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Fail to create a manager if email input missing @
     */
    @Test
    public void testCreateManagerEmailMissingAt() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        String firstName = "Joe";
        String lastName = "Doe";
        String email = "joegmail.com";
        long phoneNumber = 1234567891;
        String password = "Pass";
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("The email is invalid!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Fail to create a manager if email input missing dot
     */
    @Test
    public void testCreateManagerEmailMissingDot() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        String firstName = "Joe";
        String lastName = "Doe";
        String email = "joe@gmailcom";
        long phoneNumber = 1234567891;
        String password = "Pass";
        Manager manager = null;
        try {
            manager = managerService.createManager(firstName, lastName, email, phoneNumber, password);
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("The email is invalid!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Get manager from correct email of existing manager successfully
     */
    @Test
    public void testGetManagerSuccessful() {
        setMockOutput();
        Manager manager = null;
        try {
            manager = managerService.getManager("joe@gmail.com");
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        assertNotNull(manager);
        assertEquals("joe@gmail.com", manager.getEmail());

    }

    /*
     * Fail to get manager because email of non-existing manager
     */
    @Test
    public void testGetManagerUnsuccessful() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        Manager manager = null;
        try {
            manager = managerService.getManager("jane@gmail.com");
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("Manager Not Found", error);
        assertEquals(HttpStatus.NOT_FOUND, error_status);

    }

    /*
     * Get list of managers successfully
     */
    @Test
    public void testGetManagerListSuccessful() {
        setMockOutput();
        List<Manager> managers = null;
        try {
            managers = managerService.getAllManagers();
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        assertNotNull(managers);
        assertEquals("joey@gmail.com", managers.get(0).getEmail());
        assertEquals("janet@gmail.com", managers.get(1).getEmail());
    }

    /*
     * Fail to get list of managers successfully since null
     */
    @Test
    public void testGetManagerListUnsuccessful() {
        String error = null;
        HttpStatus error_status = null;
        List<Manager> managers = null;
        try {
            managers = managerService.getAllManagers();
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(managers);
        assertEquals("There are no Managers Found", error);
        assertEquals(HttpStatus.NOT_FOUND, error_status);
    }

    /*
     * Update manager password from correct email of existing manager and correct
     * old password successfully
     */
    @Test
    public void testUpdateManagerThatExists() {
        setMockOutput();
        Manager manager = null;
        try {
            manager = managerService.updateManagerPassword("joe@gmail.com", "Pass", "Passed");
        } catch (Mar1HotelSystemException e) {
            fail();
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
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        Manager manager = null;
        try {
            manager = managerService.updateManagerPassword("joe@gmail.com", "wrongPass", "Passed");
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();

        }
        assertNull(manager);
        assertEquals("Incorrect old password!", error);
        assertEquals(HttpStatus.BAD_REQUEST, error_status);
    }

    /*
     * Fail to update manager password of non-existing manager
     */
    @Test
    public void testUpdateManagerThatDoesNotExist() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        Manager manager = null;
        try {
            manager = managerService.updateManagerPassword("Hey@gmail.com", "WhatUp?", "NothingMuchYou?");
        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
        }
        assertNull(manager);
        assertEquals("User Not Found", error);
        assertEquals(HttpStatus.NOT_FOUND, error_status);
    }

    /*
     * Delete manager from correct email of existing manager successfully
     */
    @Test
    public void testDeleteManagerThatExist() {
        setMockOutput();
        Boolean manager = null;
        try {
            manager = managerService.deleteManager("joe@gmail.com");
        } catch (Mar1HotelSystemException e) {
            fail();
        }
        assertNotNull(manager);
        assertEquals(true, manager);

    }

    /*
     * Unsuccessfully delete manager of non-existing manager
     */
    @Test
    public void testDeleteManagerThatDoesNotExist() {
        setMockOutput();
        String error = null;
        HttpStatus error_status = null;
        Boolean manager = null;
        try {
            manager = managerService.deleteManager("jane@gmail.com");

        } catch (Mar1HotelSystemException e) {
            error = e.getMessage();
            error_status = e.getStatus();
            assertEquals("User with that email does not exist!", error);
            assertEquals(HttpStatus.NOT_FOUND, error_status);
        }
        assertEquals(false, manager);

    }

}
