package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.service.PaymentService;
/**
 * The controller that handles /payment endpoint requests
 * Required functionalities:
 * - Recieve payment request (POST)
 * - Return payment result as DTO
 * DTOs might used:
 * - PaymentDTO
 * 
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class PaymentRestController {
    
    @Autowired
	private PaymentService service;

    @DeleteMapping("/{paymentId}")
        public void deletePayment(@PathVariable int paymentId) {
        service.deletePaymentById(paymentId);
    }

    @GetMapping(value = { "/payment/{paymentId}", "/payment/{paymentId}/" })
    public void getPaymentById(@PathVariable int paymentId) {
        service.getPaymentById(paymentId);
    }

    @GetMapping(value = "/{paymentId}")
    public void getAllPayments() {
        service.getAllPayments();
    }

    @PostMapping("/payment/create")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        Payment payment = service.createPayment(paymentDto.getAmount());
        return ResponseEntity.ok(new PaymentDto(payment));
    }
    

}
