package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import ca.mcgill.ecse321.Mar1HotelSystem.service.AssignmentService;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.AssignmentRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.AssignmentResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Assignment;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;

/**
 * The controller that handles /assignment endpoint requests
 * Required functionalities:
 * - Display/Return all assignments (GET)
 * - Assign an employee to a room (Receive POST request)
 * - etc.
 * 
 * @author Lucas Paccico @Lucaspac5
 * @author Adam Corbier (@Ad2Am2)
 */
@CrossOrigin(origins = "*")
@RestController
public class AssignmentRestController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping(value = { "/assignments/all", "/assignments/all/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AssignmentResponseDto>> getAllAssignments() {

        List<Assignment> assignments = assignmentService.getAllAssignments();
        List<AssignmentResponseDto> assignmentResponseDtoList = new ArrayList<AssignmentResponseDto>();

        for (Assignment assignment : assignments) {
            AssignmentResponseDto assignmentResponseDto = new AssignmentResponseDto(assignment.getAssignmentId(),
                    assignment.getAssignee(), assignment.getRequest());
            assignmentResponseDtoList.add(assignmentResponseDto);
        }

        return new ResponseEntity<List<AssignmentResponseDto>>(assignmentResponseDtoList, HttpStatus.OK);
    }

    @DeleteMapping(value = { "/assignments/delete/{assignmentId}", "/assignments/delete/{assignmentId}/" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteAssignmentById(@PathVariable int assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
    }

    @GetMapping(value = { "/assignments/{assignmentId}", "/assignments/{assignmentId}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AssignmentResponseDto> getAssignmentById(@PathVariable int assignmentId) {
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        return new ResponseEntity<AssignmentResponseDto>(new AssignmentResponseDto(assignment.getAssignmentId(),
                assignment.getAssignee(), assignment.getRequest()), HttpStatus.OK);
    }

    @PostMapping(value = { "/assignment/create", "/assignments/create/" })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AssignmentResponseDto> createAssignment(
            @RequestBody AssignmentRequestDto assignmentRequestDto) {

        String employeeId = assignmentRequestDto.getEmployeeId();
        int requestId = assignmentRequestDto.getRequestId();

        Assignment assignment = assignmentService.createAssignment(employeeId, requestId);

        return new ResponseEntity<AssignmentResponseDto>(new AssignmentResponseDto(assignment.getAssignmentId(),
                assignment.getAssignee(), assignment.getRequest()), HttpStatus.CREATED);
    }

    @PutMapping(value = { "/assignments/update/{assignmentId}", "/assignments/update/{assignmentId}/" })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AssignmentResponseDto> updateAssignment(@PathVariable int assignmentId,
            @RequestBody AssignmentResponseDto assignmentDto) {

        Employee employee = assignmentDto.getEmployee();
        Request request = assignmentDto.getRequest();

        assignmentService.updateAssignment(assignmentId, employee, request);

        Assignment assignment = assignmentService.getAssignmentById(assignmentId);

        return new ResponseEntity<AssignmentResponseDto>(new AssignmentResponseDto(assignment.getAssignmentId(),
                assignment.getAssignee(), assignment.getRequest()), HttpStatus.OK);
    }

}
