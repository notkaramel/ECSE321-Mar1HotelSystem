package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RequestRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.AssignmentRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Assignment;
import jakarta.transaction.Transactional;

/**
 * Service class and methods for Assignment entity
 * 
 * @author Antoine Phan (@notkaramel)
 */
@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RequestRepository requestRepository;

    @Transactional
    public Assignment createAssignment(Employee assignee, Request request) {
        Assignment assignment = new Assignment();
        assignment.setEmployee(assignee);
        assignment.setRequest(request);
        assignmentRepository.save(assignment);
        return assignment;
    }

    @Transactional
    public Assignment getAssignmentById(int assignmentId) {
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);
        return assignment;
    }

    @Transactional
    public boolean deleteAssignment(int assignmentId) {
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);
        try {
            assignmentRepository.delete(assignment);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Assignment updateAssignment(int assignmentId, Employee assignee, Request request) {
        Assignment assignment = assignmentRepository.findAssignmentByAssignmentId(assignmentId);
        if (assignment == null) {
            return null;
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
