package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;

public class PaymentIntegrationTest {
    
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TestRestTemplate paymentClient;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        paymentRepository.deleteAll();
    }
    @Test
    public int testCreatePayment() {
        PaymentDto paymentDto = new PaymentDto(100);
        ResponseEntity<PaymentDto> response = paymentClient.postForEntity("/payment", paymentDto, PaymentDto.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        return response.getBody().getPaymentId();
    }
    
}
