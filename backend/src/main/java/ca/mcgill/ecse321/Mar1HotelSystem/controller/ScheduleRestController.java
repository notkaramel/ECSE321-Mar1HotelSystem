package ca.mcgill.ecse321.Mar1HotelSystem.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins="*")
@RestController

public class ScheduleRestController {
    
    @Autowired
    private ScheduleService service;

    //operating hours
    @GetMapping(value = {"/operatingHours/{day}", "/operatingHours/{day}/"})
    public ResponseEntity<OperatingHoursResponseDto> getOperatingHoursByDay(@PathVariable DayOfWeek day) {
        OperatingHours oh = service.getOperatingHoursByDay(day);
        return new ResponseEntity<OperatingHoursResponseDto>(new OperatingHoursResponseDto(oh), HttpStatus.OK);
    }

    @GetMapping(value = {"/operatingHours", "/operatingHours/"})
    public ResponseEntity<OperatingHoursMultipleResponseDto> getAllOperatingHours() {
        Iterable<OperatingHours> ohList = service.getAllOperatingHours();
        OperatingHoursMultipleResponseDto ohResponse = new OperatingHoursMultipleResponseDto(ohList);
        return new ResponseEntity<OperatingHoursMultipleResponseDto>(ohResponse, HttpStatus.OK);
    }
    
    @PostMapping(value = {"/operatingHours", "/operatingHours/"})
    public ResponseEntity<OperatingHoursResponseDto> createOperatingHours(@RequestBody OperatingHoursRequestDto request) {
        OperatingHours ohToCreate = request.toModel();

        DayOfWeek day = ohToCreate.getDayOfWeek();
        int openingHour = ohToCreate.getOpeningHour();
        int closingHour = ohToCreate.getClosingHour();

        OperatingHours oh = service.createOperatingHours(day, openingHour, closingHour);
    
        OperatingHoursResponseDto response = new OperatingHoursResponseDto(oh);
        return new ResponseEntity<OperatingHoursResponseDto>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = {"/operatingHours", "/operatingHours/"})
    public ResponseEntity<OperatingHoursResponseDto> updateOperatingHours(@RequestBody OperatingHoursRequestDto request) {
        OperatingHours ohToUpdate = request.toModel();

        DayOfWeek day = ohToUpdate.getDayOfWeek();
        int openingHour = ohToUpdate.getOpeningHour();
        int closingHour = ohToUpdate.getClosingHour();

        OperatingHours oh = service.updateOperatingHours(day, openingHour, closingHour);
    
        OperatingHoursResponseDto response = new OperatingHoursResponseDto(oh);
        return new ResponseEntity<OperatingHoursResponseDto>(response, HttpStatus.OK);
    }
    

    //custom hours
    @GetMapping( value = {"/customHours/{date}", "/customHours/{date}/"})
    public ResponseEntity<CustomHoursResponseDto> getCustomHoursByDate(@PathVariable Date date) {
        CustomHours ch = service.getCustomHoursByDate(date);
        return new ResponseEntity<CustomHoursResponseDto>(new CustomHoursResponseDto(ch), HttpStatus.OK);
    }

    @GetMapping(value = {"/customHours", "/customHours/"})
    public ResponseEntity<CustomHoursMultipleResponseDto> getAllCustomHours() {
        Iterable<CustomHours> chList = service.getAllCustomHours();
        CustomHoursMultipleResponseDto chResponse = new CustomHoursMultipleResponseDto(chList);
        return new ResponseEntity<CustomHoursMultipleResponseDto>(chResponse, HttpStatus.OK);
    }

    @PostMapping(value = {"/customHours", "/customHours/"})
    public ResponseEntity<CustomHoursResponseDto> createCustomHours(@RequestBody CustomHoursRequestDto request) {
        CustomHours chToCreate = request.toModel();

        Date date = chToCreate.getDate();
        int openingHour = chToCreate.getOpeningHour();
        int closingHour = chToCreate.getClosingHour();

        CustomHours ch = service.createCustomHours(date, openingHour, closingHour);
    
        CustomHoursResponseDto response = new CustomHoursResponseDto(ch);
        return new ResponseEntity<CustomHoursResponseDto>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = {"/customHours", "/customHours/"})
    public ResponseEntity<CustomHoursResponseDto> updateCustomHours(@RequestBody CustomHoursRequestDto request) {
        CustomHours chToUpdate = request.toModel();

        Date date = chToUpdate.getDate();
        int openingHour = chToUpdate.getOpeningHour();
        int closingHour = chToUpdate.getClosingHour();

        CustomHours ch = service.updateCustomHours(date, openingHour, closingHour);
    
        CustomHoursResponseDto response = new CustomHoursResponseDto(ch);
        return new ResponseEntity<CustomHoursResponseDto>(response, HttpStatus.OK);
    }
    
    @PostMapping(value = {"/customHours", "/customHours/"})
    public ResponseEntity<CustomHoursResponseDto> deleteCustomHours(@RequestBody CustomHoursRequestDto request) {
        CustomHours chToDelete = request.toModel();

        Date date = chToDelete.getDate();

        boolean ch = service.deleteCustomHours(date);
    
        CustomHoursResponseDto response = new CustomHoursResponseDto(ch); 
        return new ResponseEntity<CustomHoursResponseDto>(response, HttpStatus.OK); 
    }

    //hotel schedule
    @GetMapping(value = {"/hotelSchedule/{year}", "/hotelSchedule/{year}/"})
    public ResponseEntity<HotelScheduleResponseDto> getHotelScheduleByYear(@PathVariable int year) {
        HotelSchedule hs = service.getHotelScheduleByYear(year);
        return new ResponseEntity<HotelScheduleResponseDto>(new HotelScheduleResponseDto(hs), HttpStatus.OK);
    }

    @GetMapping(value = {"/hotelSchedule", "/hotelSchedule/"})
    public ResponseEntity<HotelScheduleMultipleResponseDto> getAllHotelSchedules() {
        Iterable<HotelSchedule> hsList = service.getAllHotelSchedules();
        HotelScheduleMultipleResponseDto hsResponse = new HotelScheduleMultipleResponseDto(hsList);
        return new ResponseEntity<HotelScheduleMultipleResponseDto>(hsResponse, HttpStatus.OK);
    }

    @PostMapping(value = {"/hotelSchedule", "/hotelSchedule/"})
    public ResponseEntity<HotelScheduleResponseDto> createHotelSchedule(@RequestBody HotelScheduleRequestDto request) {
        HotelSchedule hsToCreate = request.toModel();

        int year = hsToCreate.getYear();
        List<OperatingHours> ohList = hsToCreate.getOperatingHours();
        List<CustomHours> chList = hsToCreate.getCustomHours();
        
        OperatingHours[] operatingHoursArray = ohList.toArray(new OperatingHours[0]);
        CustomHours[] customHoursArray = chList.toArray(new CustomHours[0]);

        HotelSchedule hs = service.createHotelSchedule(year, operatingHoursArray, customHoursArray);
    
        HotelScheduleResponseDto response = new HotelScheduleResponseDto(hs);
        return new ResponseEntity<HotelScheduleResponseDto>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = {"/hotelSchedule", "/hotelSchedule/"})
    public ResponseEntity<HotelScheduleResponseDto> deleteHotelSchedule(@RequestBody HotelScheduleRequestDto request) {
        HotelSchedule hsToDelete = request.toModel();

        int year = hsToDelete.getYear();

        boolean hs = service.deleteHotelSchedule(year);
    
        HotelScheduleResponseDto response = new HotelScheduleResponseDto(hs); 
        return new ResponseEntity<HotelScheduleResponseDto>(response, HttpStatus.OK); 
}

