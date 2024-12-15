package com.uetravel.controllers;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.Assignings;
import com.uetravel.services.AssigningsServices;

@RestController
@RequestMapping("/assignings")
public class AssigningsController {
    @Autowired
    private AssigningsServices assigningsServices;

    @GetMapping
    public List<Assignings> getAllAssignings() {
        return assigningsServices.getAllAssignings();
    }

    @GetMapping("/employeeName/{employeeName}")
    public ResponseEntity<List<Assignings>> getAssigningsByEmployeeName(@PathVariable String employeeName) {
        List<Assignings> assignings = assigningsServices.getAssigningsByEmployeeName(employeeName);
        return assignings.isEmpty() ? 
                ResponseEntity.noContent().build() : 
                ResponseEntity.ok(assignings);
    }

    @GetMapping("/employeeId/{employeeId}")
    public ResponseEntity<List<Assignings>> getAssigningsByEmployeeId(@PathVariable Integer employeeId) {
        List<Assignings> assignings = assigningsServices.getAssigningsByEmployeeId(employeeId);
        return assignings.isEmpty() ? 
                ResponseEntity.noContent().build() : 
                ResponseEntity.ok(assignings);
    }

    @GetMapping("/vehicle/{registrationNumber}")
    public ResponseEntity<List<Assignings>> getAssigningsByVehicleRegistrationNumber(@PathVariable String registrationNumber) {
        List<Assignings> assignings = assigningsServices.getAssigningsByVehicleRegistrationNumber(registrationNumber);
        return assignings.isEmpty() ? 
                ResponseEntity.noContent().build() : 
                ResponseEntity.ok(assignings);
    }

    @GetMapping("/tour/{tourName}")
    public ResponseEntity<List<Assignings>> getAssigningsByTourName(@PathVariable String tourName) {
        List<Assignings> assignings = assigningsServices.getAssigningsByTourName(tourName);
        return assignings.isEmpty() ? 
                ResponseEntity.noContent().build() : 
                ResponseEntity.ok(assignings);
    }

    @DeleteMapping("/{assigningId}")
    public ResponseEntity<Void> deleteAssigning(@PathVariable Integer assigningId) {
        try {
            assigningsServices.deleteAssigning(assigningId); 
            return ResponseEntity.noContent().build(); 
        } catch (IllegalArgumentException e) { 
            return ResponseEntity.notFound().build(); 
        }
    }

    @PatchMapping("/{assigningId}/driver")
    public ResponseEntity<Assignings> updateAssigningDriver(
        @PathVariable Integer assigningId,
        @RequestParam("driverName") String driverName) {

        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningDriver(assigningId, driverName);
            return ResponseEntity.ok(updatedAssigning);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{assigningId}/tourGuide")
    public ResponseEntity<Assignings> updateAssigningTourGuide(
        @PathVariable Integer assigningId,
        @RequestParam("tourGuideName") String tourGuideName) {

        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningTourGuide(assigningId, tourGuideName);
            return ResponseEntity.ok(updatedAssigning);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{assigningId}/startTime")
    public ResponseEntity<Assignings> updateAssigningStartTime(
        @PathVariable Integer assigningId,
        @RequestParam("startTime") Timestamp startTime) {

        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningStartTime(assigningId, startTime);
            return ResponseEntity.ok(updatedAssigning);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{assigningId}/endTime")
    public ResponseEntity<Assignings> updateAssigningEndTime(
        @PathVariable Integer assigningId,
        @RequestParam("endTime") Timestamp endTime) {

        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningEndTime(assigningId, endTime);
            return ResponseEntity.ok(updatedAssigning);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{assigningId}/startDestination")
    public ResponseEntity<Assignings> updateAssigningStartDestination(
        @PathVariable Integer assigningId,
        @RequestParam("startDestinationName") String startDestinationName) {

        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningStartDestination(assigningId, startDestinationName);
            return ResponseEntity.ok(updatedAssigning);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{assigningId}/endDestination")
    public ResponseEntity<Assignings> updateAssigningEndDestination(
        @PathVariable Integer assigningId,
        @RequestParam("endDestinationName") String endDestinationName) {

        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningEndDestination(assigningId, endDestinationName);
            return ResponseEntity.ok(updatedAssigning);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{assigningId}/vehicle")
    public ResponseEntity<Assignings> updateAssigningVehicle(
        @PathVariable Integer assigningId,
        @RequestParam("registrationNumber") String registrationNumber) { 

        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningVehicle(assigningId, registrationNumber);
            return ResponseEntity.ok(updatedAssigning);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{assigningId}/tour")
    public ResponseEntity<Assignings> updateAssigningTour(
        @PathVariable Integer assigningId,
        @RequestParam("tourName") String tourName) { 

        try {
            Assignings updatedAssigning = assigningsServices.updateAssigningTour(assigningId, tourName);
            return ResponseEntity.ok(updatedAssigning);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}