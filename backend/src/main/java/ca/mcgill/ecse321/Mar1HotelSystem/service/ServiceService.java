package ca.mcgill.ecse321.Mar1HotelSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.Mar1HotelSystem.dao.EmployeeRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.RequestRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.dao.ServiceRepository;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Employee;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Request;
import ca.mcgill.ecse321.Mar1HotelSystem.model.Service;
import jakarta.transaction.Transactional;

/**
 * Service class and methods for Service entity
 * 
 * @author Antoine Phan (@notkaramel)
 */
@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RequestRepository requestRepository;

    @Transactional
    public Service createService(Employee assignee, Request request) {
        Service service = new Service();
        service.setEmployee(assignee);
        service.setRequest(request);
        serviceRepository.save(service);
        return service;
    }

    @Transactional
    public Service getServiceById(int serviceId) {
        Service service = serviceRepository.findServiceByServiceId(serviceId);
        return service;
    }

    @Transactional
    public boolean deleteService(int serviceId) {
        Service service = serviceRepository.findServiceByServiceId(serviceId);
        if (service == null) {
            return false;
        }
        serviceRepository.delete(service);
        return true;
    }

    @Transactional
    public Service updateService(int serviceId, Employee assignee, Request request) {
        Service service = serviceRepository.findServiceByServiceId(serviceId);
        if (service == null) {
            return null;
        }
        service.setEmployee(assignee);
        service.setRequest(request);
        serviceRepository.save(service);
        return service;
    }

    @Transactional
    public List<Service> getAllServices() {
        return ServiceUtils.toList(serviceRepository.findAll());
    }
}
