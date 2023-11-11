package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Simple main system integration tests
 * 
 * @author Antoine Phan (@notkaramel)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Mar1HotelSystemIntegrationTest {
    @Autowired
    private TestRestTemplate client;

    @Test
    public void mainTest() {
        testPing();
        testCoffee();
    }

    public void testPing() {
        ResponseEntity<String> response = client.getForEntity("/", String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    public void testCoffee() {
        ResponseEntity<String> response = client.getForEntity("/coffee", String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.I_AM_A_TEAPOT);
    }

}
