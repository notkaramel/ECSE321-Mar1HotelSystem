package ca.mcgill.ecse321.Mar1HotelSystem.dto;

import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;

public class AssignmentResponseDto {

    // VARIABLES
    private Employee employee;
    private Request request;
    private int assignmentId;

    // CONSTRUCTORS

    public AssignmentResponseDto() {
    }

    // Assignment constructor requiring employee and request
    public AssignmentResponseDto(int assignmentId, Employee employee, Request request) {
        this.assignmentId = assignmentId;
        this.employee = employee;
        this.request = request;
    }

    // GETTERS
    // Method to get employee, returns employee
    public Employee getEmployee() {
        return this.employee;
    }

    // Method to get request, returns request
    public Request getRequest() {
        return this.request;
    }

    public int getAssignmentId() {
        return this.assignmentId;
    }
    
}
