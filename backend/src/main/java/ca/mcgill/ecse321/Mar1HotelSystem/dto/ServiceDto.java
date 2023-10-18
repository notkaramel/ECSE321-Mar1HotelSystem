package ca.mcgill.ecse321.Mar1HotelSystem.model;

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

    // SETTERS 
    // Method to set assignee, returns true if assignee set
    public boolean setEmployee(EmployeeDto assignee) {
        if (assignee != null) {
            this.assignee = assignee;
            return true;
        } else {
            return false;
        }
    }

    // Method to set request, returns true if request set
    public boolean setRequest(RequestDto request) {
        if (request != null) {
            this.request = request;
            return true;
        } else {
            return false;
        }
    }

}
