// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;
    
    @OneToOne
    private Employee assignee;
    @OneToOne
    private Request request;
    public Service() {

    }

    // Service constructor requiring assignee and request
    public Service(Employee assignee, Request request) {
        if (setEmployee(assignee) == false) {
            throw new RuntimeException("Need an employee class to be instatiated; need an employee");
        }

        if (setRequest(request) == false) {
            throw new RuntimeException("Need an booking class to be instatiated; need a booking");
        }

    }

    // Getters
    // Method to get assignee, returns assignee
    public Employee getAssignee() {
        return this.assignee;
    }

    // Method to get request, returns request
    public Request getRequest() {
        return this.request;
    }

    // Setters
    // Method to set assignee, returns true if assignee set
    public boolean setEmployee(Employee assignee) {
        if (assignee != null) {
            this.assignee = assignee;
            return true;
        } else {
            return false;
        }
    }

    // Method to set request, returns true if request set
    public boolean setRequest(Request request) {
        if (request != null) {
            this.request = request;
            return true;
        } else {
            return false;
        }
    }

    public int getServiceId() {
        return serviceId;
    }

    public void delete() {
        this.assignee = null;
        this.request = null;
    }
}
