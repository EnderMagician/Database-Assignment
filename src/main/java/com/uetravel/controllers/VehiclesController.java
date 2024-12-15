package com.uetravel.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.Vehicles;
import com.uetravel.services.VehiclesServices;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

    @Autowired
    private VehiclesServices vehiclesServices;

    @GetMapping
    public List<Vehicles> getAllVehicles() {
        return vehiclesServices.getAllVehicles();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Vehicles>> getVehiclesByType(@PathVariable String type) {
        List<Vehicles> vehicles = vehiclesServices.getVehiclesByType(type);
        return vehicles.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(vehicles);
    }

    @GetMapping("/registrationNumber/{registrationNumber}")
    public ResponseEntity<Vehicles> getVehicleByRegistrationNumber(@PathVariable String registrationNumber) {
        Vehicles vehicle = vehiclesServices.getVehicleByRegistrationNumber(registrationNumber);
        return vehicle == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(vehicle);
    }

    @GetMapping("/purchasedDate/{purchasedDate}")
    public ResponseEntity<List<Vehicles>> getVehiclesByPurchasedDate(@PathVariable Date purchasedDate) {
        List<Vehicles> vehicles = vehiclesServices.getVehiclesByPurchasedDate(purchasedDate);
        return vehicles.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(vehicles);
    }

    @DeleteMapping("/{registrationNumber}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String registrationNumber) {
        try {
            vehiclesServices.deleteVehicle(registrationNumber);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}