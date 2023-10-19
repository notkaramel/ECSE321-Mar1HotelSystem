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
