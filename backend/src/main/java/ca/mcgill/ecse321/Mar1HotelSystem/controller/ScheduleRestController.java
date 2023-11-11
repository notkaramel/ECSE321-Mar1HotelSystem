package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomHoursMultipleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomHoursRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.CustomHoursResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleMultipleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.HotelScheduleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursMultipleResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursRequestDto;
import ca.mcgill.ecse321.Mar1HotelSystem.dto.OperatingHoursResponseDto;
import ca.mcgill.ecse321.Mar1HotelSystem.model.CustomHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.HotelSchedule;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours;
import ca.mcgill.ecse321.Mar1HotelSystem.model.OperatingHours.DayOfWeek;
import ca.mcgill.ecse321.Mar1HotelSystem.service.ScheduleService;

/**
 * This controller handles everything related to the schedule of the hotel.
 * It is responsible for creating, updating, deleting, and retrieving the operating hours, custom hours, and hotel schedule.
 * 
 * @author: Emma Friesen (@emma-friesen)
 * @author: Antoine Phan (@notkaramel)
 */
@CrossOrigin(origins="*")
@RestController

public class ScheduleRestController {
    
    @Autowired
    private ScheduleService service;

    // Operating Hours
    @GetMapping(value = {"/operatingHours/day/{day}"}) 
    public ResponseEntity<OperatingHoursResponseDto> getOperatingHoursByDay(@PathVariable("day") DayOfWeek day) {
        OperatingHours oh = service.getOperatingHoursByDay(day);
        return new ResponseEntity<OperatingHoursResponseDto>(new OperatingHoursResponseDto(oh), HttpStatus.OK);
    }

    @GetMapping(value = {"/operatingHours"}) 
    public ResponseEntity<OperatingHoursMultipleResponseDto> getAllOperatingHours() {
        List<OperatingHours> ohList = service.getAllOperatingHours();
        OperatingHoursMultipleResponseDto ohResponse = new OperatingHoursMultipleResponseDto(ohList);
        return new ResponseEntity<OperatingHoursMultipleResponseDto>(ohResponse, HttpStatus.OK);
    }
    
    @PostMapping(value = {"/operatingHours/create"}) 
    public ResponseEntity<OperatingHoursResponseDto> createOperatingHours(@RequestBody OperatingHoursRequestDto request) {
        DayOfWeek day = request.getDayOfWeek();
        int openingHour = request.getOpeningHour();
        int closingHour = request.getClosingHour();

        OperatingHours oh = service.createOperatingHours(day, openingHour, closingHour);
    
        OperatingHoursResponseDto response = new OperatingHoursResponseDto(oh);
        return new ResponseEntity<OperatingHoursResponseDto>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = {"/operatingHours/update"})
    public ResponseEntity<OperatingHoursResponseDto> updateOperatingHours(@RequestBody OperatingHoursRequestDto request) {
        DayOfWeek day = request.getDayOfWeek();
        int openingHour = request.getOpeningHour();
        int closingHour = request.getClosingHour();

        OperatingHours oh = service.updateOperatingHoursByDay(day, openingHour, closingHour);
    
        OperatingHoursResponseDto response = new OperatingHoursResponseDto(oh);
        return new ResponseEntity<OperatingHoursResponseDto>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = {"/operatingHours/delete/{day}"})
    public ResponseEntity<OperatingHoursResponseDto> deleteOperatingHoursByDay(@PathVariable("day") DayOfWeek day) {
        OperatingHours deletedOperatingHours = service.deleteOperatingHoursByDay(day);
        return new ResponseEntity<OperatingHoursResponseDto>(new OperatingHoursResponseDto(deletedOperatingHours), HttpStatus.OK);
    }

    

    // Custom Hours
    @GetMapping( value = {"/customHours/date/{date}"})
    public ResponseEntity<CustomHoursResponseDto> getCustomHoursByDate(@PathVariable("date") Date date) {
        CustomHours ch = service.getCustomHoursByDate(date);
        return new ResponseEntity<CustomHoursResponseDto>(new CustomHoursResponseDto(ch), HttpStatus.OK);
    }

    @GetMapping("/customHours/id/{id}")
    public ResponseEntity<CustomHoursResponseDto> getCustomHoursById(@PathVariable("id") int id) {
        CustomHours ch = service.getCustomHoursById(id);
        return new ResponseEntity<CustomHoursResponseDto>(new CustomHoursResponseDto(ch), HttpStatus.OK);
    }

    @GetMapping(value = {"/customHours"})
    public ResponseEntity<CustomHoursMultipleResponseDto> getAllCustomHours() {
        Iterable<CustomHours> chList = service.getAllCustomHours();
        CustomHoursMultipleResponseDto chResponse = new CustomHoursMultipleResponseDto(chList);
        return new ResponseEntity<CustomHoursMultipleResponseDto>(chResponse, HttpStatus.OK);
    }

    @PostMapping(value = {"/customHours/create"})
    public ResponseEntity<CustomHoursResponseDto> createCustomHours(@RequestBody CustomHoursRequestDto request) {
        CustomHours chToCreate = request.toModel();

        Date date = chToCreate.getDate();
        int openingHour = chToCreate.getOpeningHour();
        int closingHour = chToCreate.getClosingHour();

        CustomHours ch = service.createCustomHours(date, openingHour, closingHour);
    
        CustomHoursResponseDto response = new CustomHoursResponseDto(ch);
        return new ResponseEntity<CustomHoursResponseDto>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = {"/customHours/update"})
    public ResponseEntity<CustomHoursResponseDto> updateCustomHours(@RequestBody CustomHoursRequestDto request) {
        CustomHours chToUpdate = request.toModel();

        Date date = chToUpdate.getDate();
        int openingHour = chToUpdate.getOpeningHour();
        int closingHour = chToUpdate.getClosingHour();

        CustomHours ch = service.updateCustomHoursByDate(date, openingHour, closingHour);
    
        CustomHoursResponseDto response = new CustomHoursResponseDto(ch);
        return new ResponseEntity<CustomHoursResponseDto>(response, HttpStatus.OK);
    }
    
    @DeleteMapping(value = {"customHours/delete/{date}"})
    public ResponseEntity<CustomHoursResponseDto> deleteCustomHoursByDate(@PathVariable("date") Date date) {
        CustomHours deletedCustomHours = service.deleteCustomHoursByDate(date);
        return new ResponseEntity<CustomHoursResponseDto>(new CustomHoursResponseDto(deletedCustomHours), HttpStatus.OK);
    }

    //hotel schedule
    @GetMapping(value = {"/hotelSchedule/year/{year}"})
    public ResponseEntity<HotelScheduleResponseDto> getHotelScheduleByYear(@PathVariable("year") int year) {
        HotelSchedule hs = service.getHotelScheduleByYear(year);
        return new ResponseEntity<HotelScheduleResponseDto>(new HotelScheduleResponseDto(hs), HttpStatus.OK);
    }

    @GetMapping(value = {"/hotelSchedule"})
    public ResponseEntity<HotelScheduleMultipleResponseDto> getAllHotelSchedules() {
        Iterable<HotelSchedule> hsList = service.getAllHotelSchedules();
        HotelScheduleMultipleResponseDto hsResponse = new HotelScheduleMultipleResponseDto(hsList);
        return new ResponseEntity<HotelScheduleMultipleResponseDto>(hsResponse, HttpStatus.OK);
    }

    @PostMapping(value = {"/hotelSchedule/create"})
    public ResponseEntity<HotelScheduleResponseDto> createHotelSchedule(@RequestBody HotelScheduleRequestDto hotelScheduleRequestDto) {
        HotelSchedule hs = service.createHotelSchedule(hotelScheduleRequestDto);
        HotelScheduleResponseDto response = new HotelScheduleResponseDto(hs);
        return new ResponseEntity<HotelScheduleResponseDto>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = {"/hotelSchedule/delete"})
    public ResponseEntity<HotelScheduleResponseDto> deleteHotelSchedule(@RequestBody HotelScheduleRequestDto request) {
        int year = request.getYear();

        HotelSchedule deletedHs = service.deleteHotelSchedule(year);
    
        HotelScheduleResponseDto response = new HotelScheduleResponseDto(deletedHs); 
        return new ResponseEntity<HotelScheduleResponseDto>(response, HttpStatus.OK); 
    }
}

