package ca.mcgill.ecse321.Mar1HotelSystem.controller;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomerDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The controller that handles /customer endpoint requests
 * Required functionalities:
 * - Display user's profile (GET)
 * - Display their booking (GET)
 * - etc.
 * DTOs might used:
 * - CustomerDto
 * - BookingDto
 * - RoomDto
 * - etc.
 * @author Lucas Paccico @Lucaspac5
 */
@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {
    
    @Autowired
	private CustomerService customerService;

    @GetMapping(value = { "/customer/{email}", "/customer/{email}/" })
    public CustomerDto getCustomer(@PathVariable("email") String email) {
        return convertToDto(customerService.getCustomer(email));
    }

    @GetMapping(value = { "/customers", "/customers/" })
    public List<CustomerDto> getCustomers() {
        return customerService.getAllCustomers().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private CustomerDto convertToDto(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("There is no such customer!");
        }
        return new CustomerDto(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getPassword());
    }

    private  Customer convertToDomainObject(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("There is no such customer!");
        }
        return new Customer(
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getPhoneNumber(),
                customerDto.getPassword());
    }
}
