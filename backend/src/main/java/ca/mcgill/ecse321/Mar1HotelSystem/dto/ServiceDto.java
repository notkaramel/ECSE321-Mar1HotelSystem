package ca.mcgill.ecse321.Mar1HotelSystem.dto;

public class ServiceDto {
    // VARIABLES
    private int serviceId;
    private EmployeeDto assignee;
    private RequestDto request;

    // CONSTRUCTORS
    public ServiceDto() {

    }

    // Service constructor requiring assignee and request
    public ServiceDto(EmployeeDto assignee, RequestDto request) {
        if (setEmployee(assignee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }

        if (setRequest(request) == false) {
            throw new RuntimeException("Need an booking class to be instatiated; need a booking");
        }

    }

    // GETTERS
    // Method to get assignee, returns assignee
    public EmployeeDto getAssignee() {
        return this.assignee;
    }

    // Method to get request, returns request
    public RequestDto getRequest() {
        return this.request;
    }

    public int getServiceId() {
        return serviceId;
    }

}
