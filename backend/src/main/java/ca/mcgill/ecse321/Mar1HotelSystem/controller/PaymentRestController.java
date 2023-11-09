package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.MultiplePaymentDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.PaymentResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
public class PaymentRestController {

    @Autowired
    private PaymentService service;

    @DeleteMapping("/payment/delete/{paymentId}")
    public ResponseEntity<Void> deletePaymentById(@PathVariable("paymentId") int paymentId) {
        service.deletePaymentById(paymentId);
        return ResponseEntity.noContent().build(); // Changed to no content as it's a delete operation
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentResponseDto> getPaymentById(@PathVariable int paymentId) {
        Payment payment = service.getPaymentById(paymentId);
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto(payment);
        return new ResponseEntity<>(paymentResponseDto, HttpStatus.OK);
    }

    @GetMapping("/payment/all")
    public ResponseEntity<MultiplePaymentDto> getAllPayments() {
        Iterable<Payment> payments = service.getAllPayments();
        MultiplePaymentDto paymentDtos = new MultiplePaymentDto(payments);
        return new ResponseEntity<>(paymentDtos, HttpStatus.OK);
    }

    @PostMapping("/payment/create")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        Payment payment = service.createPayment(paymentRequestDto.getAmount());
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto(payment);
        return new ResponseEntity<>(paymentResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/payment/update/{paymentId}")
    public ResponseEntity<PaymentResponseDto> updatePayment(@PathVariable("paymentId") int paymentId,
            @RequestBody PaymentRequestDto paymentRequestDto) {
        service.updatePayment(paymentId, paymentRequestDto.getAmount());
        Payment payment = service.getPaymentById(paymentId);
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto(payment);
        return new ResponseEntity<>(paymentResponseDto, HttpStatus.OK);
    }
}
