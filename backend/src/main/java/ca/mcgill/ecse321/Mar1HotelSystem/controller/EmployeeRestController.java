package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.EmployeeDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ShiftRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.ShiftResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.Mar1HotelSystem.service.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The controller that handles /employee endpoint requests
 * Required functionality:
 * - Display employee's profile (GET)
 * - Create an employee (POST)
 * - Update an employee (PUT)
 * - Delete an employee (DELETE)
 * - Display employee's shifts (GET)
 * - Create a shift (POST)
 * - Update a shift (PUT)
 * - Delete a shift (DELETE)
 * DTOs that might be used:
 * - EmployeeDto
 * - ShiftDto
 * 
 * @author Lucas Paccico (@Lucaspac5)
 * @author ZiXu Liu (@ARandomPi)
 */
@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    // GET MAPPINGS
    @GetMapping(value = { "/employee/{email}", "/employee/{email}/" })
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeDto getEmployee(@PathVariable("email") String email) {
        return convertToDto(employeeService.getEmployee(email));
    }

    @GetMapping(value = { "/employees", "/employees/" })
    @ResponseStatus(value = HttpStatus.OK)
    public List<EmployeeDto> getEmployees() {
        return employeeService.getAllEmployees().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping(value = { "/employee/{email}/shifts", "/employee/{email}/shifts/" })
    @ResponseStatus(value = HttpStatus.OK)
    public List<ShiftResponseDto> getEmployeeShifts(@PathVariable("email") String email) {
        return employeeService.getShiftsEmployee(email).stream().map(this::convertToDtoResponse).collect(Collectors.toList());
    }

    @GetMapping(value = { "/employee/shifts", "/employee/shifts/" })
    @ResponseStatus(value = HttpStatus.OK)
    public List<ShiftResponseDto> getShifts() {
        return employeeService.getAllShifts().stream().map(this::convertToDtoResponse).collect(Collectors.toList());
    }

    @GetMapping(value = { "/employee/shift/{shiftId}", "/employee/shift/{shiftId}/" })
    @ResponseStatus(value = HttpStatus.OK)
    public ShiftResponseDto getShift(@PathVariable("shiftId") int shiftId) {
        return convertToDtoResponse(employeeService.getShift(shiftId));
    }

    // POST MAPPINGS
    @PostMapping(value = { "/employee", "/employee/" })
    @ResponseStatus(value = HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return convertToDto(employeeService.createEmployee(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getPhoneNumber(),
                employeeDto.getPassword(),
                employeeDto.getHoursWorked()));
    }

    @PostMapping(value = { "/employee/{email}/shift", "/employee/{email}/shift/" })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ShiftResponseDto createShift(@PathVariable("email") String email, @RequestBody ShiftRequestDto shiftDto) {
        return convertToDtoResponse(employeeService.createShift(
                shiftDto.getDate(),
                shiftDto.getStartTime(),
                shiftDto.getEndTime(),
                email));
    }

    // UPDATE (PUT) MAPPINGS
    @PutMapping(value = { "/employee/{email}", "/employee/{email}/" })
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return convertToDto(employeeService.updateEmployee(
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getPhoneNumber(),
                employeeDto.getPassword(),
                employeeDto.getHoursWorked()));
    }

    @PutMapping(value = { "/employee/shift/{shiftId}", "/employee/shift/{shiftId}/" })
    @ResponseStatus(value = HttpStatus.OK)
    public ShiftResponseDto updateShift(@PathVariable("shiftId") int shiftId, @RequestBody ShiftRequestDto shiftDto) {
        return convertToDtoResponse(employeeService.updateShift(
                shiftId,
                shiftDto.getDate(),
                shiftDto.getStartTime(),
                shiftDto.getEndTime()));
    }

    // DELETE MAPPINGS
    @DeleteMapping(value = { "/employee/{email}", "/employee/{email}/" })
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteEmployee(@PathVariable("email") String email) {
        employeeService.deleteEmployee(email);
    }

    @DeleteMapping(value = { "/employee/shift/{shiftId}", "/employee/shift/{shiftId}/" })
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteShift(@PathVariable("shiftId") int shiftId) {
        employeeService.deleteShift(shiftId);
    }

    /**
     * Helper method to convert an employee to an employee DTO
     * 
     * @param employee the employee to convert
     * @return the employee DTO
     */
    private EmployeeDto convertToDto(Employee employee) {
        if (employee == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The employee does not exist!");
        }
        return new EmployeeDto(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getPassword(),
                employee.getHoursWorked());
    }


    /**
     * Helper method to convert a shift to a shift DTO (response)
     * 
     * @param shift the shift to convert
     * @return the shift DTO
     */
    private ShiftResponseDto convertToDtoResponse(Shift shift) {
        if (shift == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "The shift does not exist!");
        }
        return new ShiftResponseDto(
                shift.getShiftId(),
                convertToDto(shift.getEmployee()),
                shift.getDate(),
                shift.getStartTime(),
                shift.getEndTime());
    }
}
