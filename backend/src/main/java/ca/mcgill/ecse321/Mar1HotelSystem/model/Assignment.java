// Umple was used a guide and generated some code in this project
package ca.mcgill.ecse321.Mar1HotelSystem.model;

import jakarta.persistence.*;

/**
 * The Service class for all requests serviced by an employee of the system.
 * 
 * @author Lucas Pacicco (@Lucaspac5) - Boilerplate Code
 * @author Bilar Mokhtari (@bmokhtari) - JPA Annotations
 * @author Antoine Phan (@notkaramel) - JPA Annotations
 */

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int assignmentId;

    @ManyToOne
    private Employee assignee;
    @OneToOne
    private Request request;

    public Assignment() {

    }

    // Service constructor requiring assignee and request
    public Assignment(Employee assignee, Request request) {
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

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentID(int assignmentId) {
        this.assignmentId = assignmentId;
        //TODO check that it is unique
    }


    public void delete() {
        this.assignee = null;
        this.request = null;
    }
}
