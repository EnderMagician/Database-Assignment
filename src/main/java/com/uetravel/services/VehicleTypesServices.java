package com.uetravel.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.uetravel.models.VehicleTypes;
import com.uetravel.repositories.VehicleTypesRepo;

@Service
public class VehicleTypesServices {

    @Autowired
    private VehicleTypesRepo vehicleTypesRepo;

    public List<VehicleTypes> getAllVehicleTypes() {
        return vehicleTypesRepo.findAll();
    }

    public VehicleTypes getVehicleTypeByType(String type) {
        return vehicleTypesRepo.findByType(type);
    }

    public List<VehicleTypes> getVehicleTypesByCostRange(BigDecimal minCost, BigDecimal maxCost, Sort sort) {
        return vehicleTypesRepo.findByCostRange(minCost, maxCost, sort);
    }

    public List<VehicleTypes> getVehicleTypesBySeats(Integer seats) {
        return vehicleTypesRepo.findBySeats(seats);
    }

    public void deleteVehicleType(String type) {
        if (!vehicleTypesRepo.existsById(type)) {
            throw new IllegalArgumentException("Vehicle type not found with type: " + type);
        }
        vehicleTypesRepo.deleteById(type);
    }
}