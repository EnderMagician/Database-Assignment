package com.uetravel.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.VehicleTypes;
import com.uetravel.services.VehicleTypesServices;

@RestController
@RequestMapping("/vehicleTypes")
public class VehicleTypesController {

    @Autowired
    private VehicleTypesServices vehicleTypesServices;

    @GetMapping
    public ModelAndView getAllVehicleTypes() {
        ModelAndView modelAndView = new ModelAndView("vehicleTypes"); // View name: "vehicleTypes"
        List<VehicleTypes> vehicleTypes = vehicleTypesServices.getAllVehicleTypes();
        modelAndView.addObject("vehicleTypes", vehicleTypes); // Attribute name: "vehicleTypes"
        return modelAndView;
    }

    @GetMapping("/type/{type}")
    public ModelAndView getVehicleTypeByType(@PathVariable String type) {
        ModelAndView modelAndView = new ModelAndView("vehicleTypes");
        VehicleTypes vehicleType = vehicleTypesServices.getVehicleTypeByType(type);
        modelAndView.addObject("vehicleType", vehicleType); // Use "vehicleType" for single object
        return modelAndView;
    }

    @GetMapping("/costRange")
    public ModelAndView getVehicleTypesByCostRange(
            @RequestParam("minCost") BigDecimal minCost,
            @RequestParam("maxCost") BigDecimal maxCost,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") Sort.Direction sortDirection) {

        ModelAndView modelAndView = new ModelAndView("vehicleTypes");
        Sort sort = sortBy != null ? Sort.by(sortDirection, sortBy) : Sort.unsorted();
        List<VehicleTypes> vehicleTypes = vehicleTypesServices.getVehicleTypesByCostRange(minCost, maxCost, sort);
        modelAndView.addObject("vehicleTypes", vehicleTypes);
        return modelAndView;
    }

    @GetMapping("/seats/{seats}")
    public ModelAndView getVehicleTypesBySeats(@PathVariable Integer seats) {
        ModelAndView modelAndView = new ModelAndView("vehicleTypes");
        List<VehicleTypes> vehicleTypes = vehicleTypesServices.getVehicleTypesBySeats(seats);
        modelAndView.addObject("vehicleTypes", vehicleTypes);
        return modelAndView;
    }

    @DeleteMapping("/{type}")
    public ModelAndView deleteVehicleType(@PathVariable String type) {
        ModelAndView modelAndView = new ModelAndView("vehicleTypes");
        try {
            vehicleTypesServices.deleteVehicleType(type);
            modelAndView.addObject("message", "Vehicle type deleted successfully!");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Vehicle type not found");
        }
        return modelAndView;
    }
}