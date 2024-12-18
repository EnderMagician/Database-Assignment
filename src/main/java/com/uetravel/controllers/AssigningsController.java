package com.uetravel.controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.Assignings;
import com.uetravel.services.AssigningsServices;

@RestController
@RequestMapping("/assignings")
public class AssigningsController {
    @Autowired
    private AssigningsServices assigningsServices;

    @GetMapping
    public ModelAndView getAllAssignings() {
        ModelAndView modelAndView = new ModelAndView("assignings");
        List<Assignings> assignings = assigningsServices.getAllAssignings();
        modelAndView.addObject("assignings", assignings);
        return modelAndView;
    }

    @GetMapping("/employeeName/{employeeName}")
    public ModelAndView getAssigningsByEmployeeName(@PathVariable String employeeName) {
        ModelAndView modelAndView = new ModelAndView("assignings");
        List<Assignings> assignings = assigningsServices.getAssigningsByEmployeeName(employeeName);
        modelAndView.addObject("assignings", assignings);
        return modelAndView;
    }

    @GetMapping("/employeeId/{employeeId}")
    public ModelAndView getAssigningsByEmployeeId(@PathVariable Integer employeeId) {
        ModelAndView modelAndView = new ModelAndView("assignings");
        List<Assignings> assignings = assigningsServices.getAssigningsByEmployeeId(employeeId);
        modelAndView.addObject("assignings", assignings);
        return modelAndView;
    }

    @GetMapping("/vehicle/{registrationNumber}")
    public ModelAndView getAssigningsByVehicleRegistrationNumber(@PathVariable String registrationNumber) {
        ModelAndView modelAndView = new ModelAndView("assignings");
        List<Assignings> assignings = assigningsServices.getAssigningsByVehicleRegistrationNumber(registrationNumber);
        modelAndView.addObject("assignings", assignings);
        return modelAndView;
    }

    @GetMapping("/tour/{tourName}")
    public ModelAndView getAssigningsByTourName(@PathVariable String tourName) {
        ModelAndView modelAndView = new ModelAndView("assignings");
        List<Assignings> assignings = assigningsServices.getAssigningsByTourName(tourName);
        modelAndView.addObject("assignings", assignings);
        return modelAndView;
    }

    @DeleteMapping("/{assigningId}")
    public ModelAndView deleteAssigning(@PathVariable Integer assigningId) {
        ModelAndView modelAndView = new ModelAndView("assignings"); 
        try {
            assigningsServices.deleteAssigning(assigningId);
            modelAndView.addObject("message", "Assigning deleted successfully!"); 
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Assigning not found"); 
        }
        return modelAndView;
    }

    @PostMapping("/{assigningId}/driver")
    public ModelAndView updateAssigningDriver(
            @PathVariable Integer assigningId,
            @RequestParam("driverName") String driverName) {

        ModelAndView modelAndView = new ModelAndView("assignings");
        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningDriver(assigningId, driverName);
            modelAndView.addObject("assignings", List.of(updatedAssigning)); 
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Assigning or Driver not found"); 
        }
        return modelAndView;
    }

    @PostMapping("/{assigningId}/tourGuide")
    public ModelAndView updateAssigningTourGuide(
            @PathVariable Integer assigningId,
            @RequestParam("tourGuideName") String tourGuideName) {

        ModelAndView modelAndView = new ModelAndView("assignings");
        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningTourGuide(assigningId, tourGuideName);
            modelAndView.addObject("assignings", List.of(updatedAssigning));
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Assigning or Tour Guide not found");
        }
        return modelAndView;
    }

    @PostMapping("/{assigningId}/startTime")
    public ModelAndView updateAssigningStartTime(
            @PathVariable Integer assigningId,
            @RequestParam("startTime") Timestamp startTime) {

        ModelAndView modelAndView = new ModelAndView("assignings");
        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningStartTime(assigningId, startTime);
            modelAndView.addObject("assignings", List.of(updatedAssigning));
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Assigning not found");
        }
        return modelAndView;
    }

    @PostMapping("/{assigningId}/endTime")
    public ModelAndView updateAssigningEndTime(
            @PathVariable Integer assigningId,
            @RequestParam("endTime") Timestamp endTime) {

        ModelAndView modelAndView = new ModelAndView("assignings");
        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningEndTime(assigningId, endTime);
            modelAndView.addObject("assignings", List.of(updatedAssigning));
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Assigning not found");
        }
        return modelAndView;
    }

    @PostMapping("/{assigningId}/startDestination")
    public ModelAndView updateAssigningStartDestination(
            @PathVariable Integer assigningId,
            @RequestParam("startDestinationName") String startDestinationName) {

        ModelAndView modelAndView = new ModelAndView("assignings");
        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningStartDestination(assigningId, startDestinationName);
            modelAndView.addObject("assignings", List.of(updatedAssigning));
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Assigning or Start Destination not found");
        }
        return modelAndView;
    }

    @PostMapping("/{assigningId}/endDestination")
    public ModelAndView updateAssigningEndDestination(
            @PathVariable Integer assigningId,
            @RequestParam("endDestinationName") String endDestinationName) {

        ModelAndView modelAndView = new ModelAndView("assignings");
        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningEndDestination(assigningId, endDestinationName);
            modelAndView.addObject("assignings", List.of(updatedAssigning));
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Assigning or End Destination not found");
        }
        return modelAndView;
    }

    @PostMapping("/{assigningId}/vehicle")
    public ModelAndView updateAssigningVehicle(
            @PathVariable Integer assigningId,
            @RequestParam("registrationNumber") String registrationNumber) {

        ModelAndView modelAndView = new ModelAndView("assignings");
        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningVehicle(assigningId, registrationNumber);
            modelAndView.addObject("assignings", List.of(updatedAssigning));
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Assigning or Vehicle not found");
        }
        return modelAndView;
    }

    @PostMapping("/{assigningId}/tour")
    public ModelAndView updateAssigningTour(
            @PathVariable Integer assigningId,
            @RequestParam("tourName") String tourName) {

        ModelAndView modelAndView = new ModelAndView("assignings");
        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningTour(assigningId, tourName);
            modelAndView.addObject("assignings", List.of(updatedAssigning));
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Assigning or Tour not found");
        }
        return modelAndView;
    }
}