package ca.mcgill.ecse321.Mar1HotelSystem.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.MultiplePaymentDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.service.PaymentService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class PaymentIntegrationTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TestRestTemplate paymentClient;

    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        paymentRepository.deleteAll();
    }

    public int testCreatePaymentIntegration() {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setAmount(100);
        ResponseEntity<PaymentResponseDto> res = paymentClient.postForEntity("/payment/create", paymentRequestDto,
                PaymentResponseDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.CREATED, res.getStatusCode());

        return res.getBody().getPaymentId();
    }

    public void testGetPaymentByIdIntegration(int paymentId) {
        ResponseEntity<PaymentResponseDto> res = paymentClient.getForEntity("/payment/" + paymentId,
                PaymentResponseDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(paymentId, res.getBody().getPaymentId());
    }

    public void testGetAllPaymentsIntegration() {
        ResponseEntity<MultiplePaymentDto> res = paymentClient.getForEntity("/payment/all", MultiplePaymentDto.class);
        assertNotNull(res);
        assertNotNull(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(1, res.getBody().getPaymentList().size());
    }

    public void testDeletePaymentIntegration(int id) {
        // Send a DELETE request to the controller
        ResponseEntity<Void> res = paymentClient.exchange(
                "/payment/" + id,
                HttpMethod.DELETE,
                null,
                Void.class);
        assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
    }

    @Test
    public void testCreateandDeletePayment() {
        int paymentId = testCreatePaymentIntegration();
        assertNotNull(paymentId);
        testDeletePaymentIntegration(paymentId);
        try {
            paymentRepository.findPaymentByPaymentId(paymentId);
        } catch (Mar1HotelSystemException e) {
            assertThrows(Mar1HotelSystemException.class, () -> paymentService.getPaymentById(1));
        }
    }

    @Test
    public void testCreateandGetPayment() {
        int paymentId = testCreatePaymentIntegration();
        assertNotNull(paymentId);
        testGetPaymentByIdIntegration(paymentId);
    }

    @Test
    public void testCreateAndGetAllPayments() {
        testCreatePaymentIntegration();
        testGetAllPaymentsIntegration();
    }

}
