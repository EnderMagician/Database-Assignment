package com.uetravel.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.Vehicles;
import com.uetravel.services.VehiclesServices;

@Controller
@RequestMapping("/vehicles")
public class VehiclesController {

    @Autowired
    private VehiclesServices vehiclesServices;

    @GetMapping
    public ModelAndView getAllVehicles() {
        ModelAndView modelAndView = new ModelAndView("vehicles"); // View name: "vehicles"
        List<Vehicles> vehicles = vehiclesServices.getAllVehicles();
        modelAndView.addObject("vehicles", vehicles); // Attribute name: "vehicles"
        return modelAndView;
    }

    @GetMapping("/registrationNumber/{registrationNumber}")
    public ModelAndView getVehicleByRegistrationNumber(@PathVariable String registrationNumber) {
        ModelAndView modelAndView = new ModelAndView("vehicles");
        Vehicles vehicle = vehiclesServices.getVehicleByRegistrationNumber(registrationNumber);
        modelAndView.addObject("vehicle", vehicle); // Use "vehicle" for single object
        return modelAndView;
    }

    @GetMapping("/purchasedDate/{purchasedDate}")
    public ModelAndView getVehiclesByPurchasedDate(@PathVariable Date purchasedDate) {
        ModelAndView modelAndView = new ModelAndView("vehicles");
        List<Vehicles> vehicles = vehiclesServices.getVehiclesByPurchasedDate(purchasedDate);
        modelAndView.addObject("vehicles", vehicles);
        return modelAndView;
    }

    @DeleteMapping("/{registrationNumber}")
    public ModelAndView deleteVehicle(@PathVariable String registrationNumber) {
        ModelAndView modelAndView = new ModelAndView("vehicles");
        try {
            vehiclesServices.deleteVehicle(registrationNumber);
            modelAndView.addObject("message", "Vehicle deleted successfully!");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Vehicle not found");
        }
        return modelAndView;
    }
}