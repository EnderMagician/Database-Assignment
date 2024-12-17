package com.uetravel.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.Assignings;
import com.uetravel.models.Destinations;
import com.uetravel.models.Employees;
import com.uetravel.models.Tours;
import com.uetravel.models.Vehicles;
import com.uetravel.repositories.AssigningsRepo;

@Service
public class AssigningsServices {
    @Autowired
    private AssigningsRepo assigningsRepo;
    private DestinationServices destinationServices;
    private EmployeesServices employeesServices;
    private VehiclesServices vehiclesServices;
    private ToursServices toursServices;

    public List<Assignings> getAllAssignings() {
        return assigningsRepo.findAll();
    }

    public List<Assignings> getAssigningsByEmployeeName(String employeeName) {
        return assigningsRepo.findByEmployeeName(employeeName);
    }

    public List<Assignings> getAssigningsByEmployeeId(Integer employeeId) {
        return assigningsRepo.findByEmployeeId(employeeId);
    }

    public List<Assignings> getAssigningsByVehicleRegistrationNumber(String registrationNumber) {
        return assigningsRepo.findByVehicleRegistrationNumber(registrationNumber);
    }

    public List<Assignings> getAssigningsByTourName(String tourName) {
        return assigningsRepo.findByTourName(tourName);
    }

    public void deleteAssigning(Integer assigningId) {
        if (!assigningsRepo.existsById(assigningId)) {
            throw new IllegalArgumentException("Assigning not found with ID: " + assigningId);
        }
        assigningsRepo.deleteById(assigningId);
    }

    public Assignings updateAssigningDriver(Integer assigningId, String driverName) {
        Assignings assigning = assigningsRepo.findById(assigningId)
            .orElseThrow(() -> new IllegalArgumentException("Assigning not found with ID: " + assigningId));

        Employees driver = employeesServices.getEmployeeByName(driverName)
            .stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Driver not found with name: " + driverName));

        assigning.setDriver(driver);
        return assigningsRepo.save(assigning);
    }

    public Assignings updateAssigningTourGuide(Integer assigningId, String tourGuideName) {
        Assignings assigning = assigningsRepo.findById(assigningId)
            .orElseThrow(() -> new IllegalArgumentException("Assigning not found with ID: " + assigningId));

        Employees tourGuide = employeesServices.getEmployeeByName(tourGuideName)
            .stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Tour guide not found with name: " + tourGuideName));

        assigning.setTourGuide(tourGuide);
        return assigningsRepo.save(assigning);
    }

    public Assignings updateAssigningStartTime(Integer assigningId, Timestamp startTime) {
        Assignings assigning = assigningsRepo.findById(assigningId)
            .orElseThrow(() -> new IllegalArgumentException("Assigning not found with ID: " + assigningId));

        assigning.setStartTime(startTime);
        return assigningsRepo.save(assigning);
    }

    public Assignings updateAssigningEndTime(Integer assigningId, Timestamp endTime) {
        Assignings assigning = assigningsRepo.findById(assigningId)
            .orElseThrow(() -> new IllegalArgumentException("Assigning not found with ID: " + assigningId));

        assigning.setEndTime(endTime);
        return assigningsRepo.save(assigning);
    }

    public Assignings updateAssigningStartDestination(Integer assigningId, String startDestinationName) {
        Assignings assigning = assigningsRepo.findById(assigningId)
            .orElseThrow(() -> new IllegalArgumentException("Assigning not found with ID: " + assigningId));

        Destinations startDestination = destinationServices.getDestinationByName(startDestinationName)
            .stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Start destination not found with name: " + startDestinationName));

        assigning.setStartDestination(startDestination);
        return assigningsRepo.save(assigning);
    }

    public Assignings updateAssigningEndDestination(Integer assigningId, String endDestinationName) {
        Assignings assigning = assigningsRepo.findById(assigningId)
            .orElseThrow(() -> new IllegalArgumentException("Assigning not found with ID: " + assigningId));

        Destinations endDestination = destinationServices.getDestinationByName(endDestinationName)
            .stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("End destination not found with name: " + endDestinationName));

        assigning.setEndDestination(endDestination);
        return assigningsRepo.save(assigning);
    }

    public Assignings updateAssigningVehicle(Integer assigningId, String registrationNumber) {
        Assignings assigning = assigningsRepo.findById(assigningId)
            .orElseThrow(() -> new IllegalArgumentException("Assigning not found with ID: " + assigningId));

        Vehicles vehicle = vehiclesServices.getVehicleByRegistrationNumber(registrationNumber);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found with registration number: " + registrationNumber);
        }

        assigning.setVehicle(vehicle);
        return assigningsRepo.save(assigning);
    }

    public Assignings updateAssigningTour(Integer assigningId, String tourName) {
        Assignings assigning = assigningsRepo.findById(assigningId)
            .orElseThrow(() -> new IllegalArgumentException("Assigning not found with ID: " + assigningId));

        Tours tour = toursServices.getTourByName(tourName)
            .stream()
            .findFirst() 
            .orElseThrow(() -> new IllegalArgumentException("Tour not found with name: " + tourName));

        assigning.setTour(tour);
        return assigningsRepo.save(assigning);
    }
}