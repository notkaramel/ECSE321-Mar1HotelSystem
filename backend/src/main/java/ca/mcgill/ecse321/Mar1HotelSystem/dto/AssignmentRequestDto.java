package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AssignmentRequestDto {

    // VARIABLES
    @NotNull
    private String employeeId;
    @NotBlank
    private int requestId;

    // CONSTRUCTORS

    public AssignmentRequestDto() {
    }

    // Assignment constructor requiring employee and request
    public AssignmentRequestDto(String employeeId, int requestId) {
        this.employeeId = employeeId;
        this.requestId = requestId;
    }

    // GETTERS
    // Method to get employee, returns employee
    public String getEmployeeId() {
        return this.employeeId;
    }

    // Method to get request, returns request
    public int getRequestId() {
        return this.requestId;
    }

    
}
