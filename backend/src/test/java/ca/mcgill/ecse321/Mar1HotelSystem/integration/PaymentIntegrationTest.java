package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentDto;

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
    public int testCreatePaymentIntegration() {
        PaymentDto paymentDto = new PaymentDto(100);
        ResponseEntity<PaymentDto> res = paymentClient.postForEntity("/payment/create", paymentDto, PaymentDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.CREATED, res.getStatusCode());

        return res.getBody().getPaymentId();
    }

    @Test
    public void testGetPaymentByIdIntegration() {
        int paymentId = testCreatePaymentIntegration();
        ResponseEntity<PaymentDto> res = paymentClient.getForEntity("/payment/" + paymentId, PaymentDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(paymentId, res.getBody().getPaymentId());
    }

    @Test
    public void testGetAllPaymentsIntegration() {
        testCreatePaymentIntegration();
        ResponseEntity<PaymentDto[]> res = paymentClient.getForEntity("/payment/", PaymentDto[].class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(1, res.getBody().length);
    }

    @Test
    public void testDeletePaymentIntegration() {
        int paymentId = testCreatePaymentIntegration();
        ResponseEntity<PaymentDto> res = paymentClient.getForEntity("/payment/" + paymentId, PaymentDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(paymentId, res.getBody().getPaymentId());
    }

}
