package com.uetravel.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.Vehicles;
import com.uetravel.repositories.VehiclesRepo;

@Service
public class VehiclesServices {

    @Autowired
    private VehiclesRepo vehiclesRepo;

    public List<Vehicles> getAllVehicles() {
        return vehiclesRepo.findAll();
    }

    public List<Vehicles> getVehiclesByType(String type) {
        return vehiclesRepo.findByType(type);
    }

    public Vehicles getVehicleByRegistrationNumber(String registrationNumber) {
        return vehiclesRepo.findByRegistrationNumber(registrationNumber);
    }

    public List<Vehicles> getVehiclesByPurchasedDate(Date purchasedDate) {
        return vehiclesRepo.findByPurchasedDate(purchasedDate);
    }

    public void deleteVehicle(String registrationNumber) {
        if (!vehiclesRepo.existsById(registrationNumber)) {
            throw new IllegalArgumentException("Vehicle not found with registration number: " + registrationNumber);
        }
        vehiclesRepo.deleteById(registrationNumber);
    }
}