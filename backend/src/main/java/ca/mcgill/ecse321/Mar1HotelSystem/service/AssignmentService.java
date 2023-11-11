package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.exception.Mar1HotelSystemException;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.AssignmentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Assignment;
import jakarta.transaction.Transactional;

/**
 * Service class and methods for Assignment entity
 * 
 * @author Antoine Phan (@notkaramel)
 * @author Adam Corbier (@Ad2Am2)
 */
@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RequestService requestService;

    @Transactional
    public Assignment createAssignment(Employee assignee, Request request) {

        if (assignee == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Assignee cannot be null!");
        } else if (request == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Request cannot be null!");
        }

        Assignment assignment = new Assignment();
        assignment.setEmployee(assignee);
        assignment.setRequest(request);
        assignmentRepository.save(assignment);
        return assignment;
    }

    @Transactional
    public Assignment createAssignment(String assigneeId, int requestId) {

        Employee assignee = employeeService.getEmployee(assigneeId);
        Request request = requestService.getRequestById(requestId);

        Assignment assignment = new Assignment();
        assignment.setEmployee(assignee);
        assignment.setRequest(request);
        assignmentRepository.save(assignment);
        return assignment;
    }

    @Transactional
    public Assignment getAssignmentById(int assignmentId) {
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);

        if (assignment == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "No assignment found with ID: " + assignmentId);
        }

        return assignment;
    }

    @Transactional
    public boolean deleteAssignment(int assignmentId) {
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);

        if (assignment == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "No assignment found with ID: " + assignmentId);
        }

        try {
            assignmentRepository.delete(assignment);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Assignment updateAssignment(int assignmentId, Employee assignee, Request request) {

        if (assignee == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Assignee cannot be null!");
        } else if (request == null) {
            throw new Mar1HotelSystemException(HttpStatus.BAD_REQUEST, "Request cannot be null!");
        }

        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);
        if (assignment == null) {
            throw new Mar1HotelSystemException(HttpStatus.NOT_FOUND, "No assignment found with ID: " + assignmentId);
        }
        assignment.setEmployee(assignee);
        assignment.setRequest(request);
        assignmentRepository.save(assignment);
        return assignment;
    }

    @Transactional
    public List<Assignment> getAllAssignments() {
        return ServiceUtils.toList(assignmentRepository.findAll());
    }
}
