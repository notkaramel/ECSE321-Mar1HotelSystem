package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.*;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.Mar1HotelSystem.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The controller that handles /customer endpoint requests
 * Required functionalities:
 * - Display user's profile (GET)
 * - Create a customer (POST)
 * - Update a customer (PUT)
 * - Delete a customer (DELETE)
 * DTOs that might be used:
 * - CustomerDto
 * 
 * @author Lucas Paccico (@Lucaspac5)
 * @author ZiXu Liu (@ARandomPi)
 */
@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    // GET MAPPINGS
    @GetMapping(value = { "/customer/{email}", "/customer/{email}/" })
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomer(@PathVariable("email") String email) {
        return convertToDto(customerService.getCustomer(email));
    }

    @GetMapping(value = { "/customers", "/customers/" })
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getCustomers() {
        return customerService.getAllCustomers().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // POST MAPPINGS
    @PostMapping(value = { "/customer", "/customer/" })
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return convertToDto(customerService.createCustomer(
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber(),
                customerDto.getPassword()));
    }

    // UPDATE (PUT) MAPPINGS
    @PutMapping(value = { "/customer/{email}", "/customer/{email}/" })
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable("email") String email, @RequestBody CustomerDto customerDto) {
        return convertToDto(customerService.updateCustomer(
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getPhoneNumber(),
                customerDto.getPassword(),
                email));
    }

    // DELETE MAPPINGS
    @DeleteMapping(value = { "/customer/{email}", "/customer/{email}/" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("email") String email) {
        customerService.deleteCustomer(email);
    }

    /**
     * Helper method to convert a Customer to a CustomerDto
     * 
     * @param customer the customer to convert
     * @return the converted CustomerDto
     */
    private CustomerDto convertToDto(Customer customer) {
        if (customer == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The customer does not exist!");
        }
        return new CustomerDto(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getPassword());
    }
}
