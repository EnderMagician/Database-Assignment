package com.uetravel.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.VehicleTypes;
import com.uetravel.services.VehicleTypesServices;

@RestController
@RequestMapping("/vehicleTypes")
public class VehicleTypesController {

    @Autowired
    private VehicleTypesServices vehicleTypesServices;

    @GetMapping
    public List<VehicleTypes> getAllVehicleTypes() {
        return vehicleTypesServices.getAllVehicleTypes();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<VehicleTypes> getVehicleTypeByType(@PathVariable String type) {
        VehicleTypes vehicleType = vehicleTypesServices.getVehicleTypeByType(type);
        return vehicleType == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(vehicleType);
    }

    @GetMapping("/costRange")
    public ResponseEntity<List<VehicleTypes>> getVehicleTypesByCostRange(
            @RequestParam("minCost") BigDecimal minCost,
            @RequestParam("maxCost") BigDecimal maxCost,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") Sort.Direction sortDirection) {

        Sort sort = sortBy != null ? Sort.by(sortDirection, sortBy) : Sort.unsorted();
        List<VehicleTypes> vehicleTypes = vehicleTypesServices.getVehicleTypesByCostRange(minCost, maxCost, sort);
        return vehicleTypes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(vehicleTypes);
    }

    @GetMapping("/seats/{seats}")
    public ResponseEntity<List<VehicleTypes>> getVehicleTypesBySeats(@PathVariable Integer seats) {
        List<VehicleTypes> vehicleTypes = vehicleTypesServices.getVehicleTypesBySeats(seats);
        return vehicleTypes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(vehicleTypes);
    }

    @DeleteMapping("/{type}")
    public ResponseEntity<Void> deleteVehicleType(@PathVariable String type) {
        try {
            vehicleTypesServices.deleteVehicleType(type);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}