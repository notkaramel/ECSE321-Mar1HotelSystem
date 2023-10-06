package ca.mcgill.ecse321.Mar1HotelSystem.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Payment;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.PaymentRepository;

public class PaymentRepositoryTest {    //Setting up the customer repository
 @Autowired
 private PaymentRepository paymentRepository;

 //Clearing the database after the test
 @AfterEach
 public void clearDatabase() {
  paymentRepository.deleteAll();
 }

}