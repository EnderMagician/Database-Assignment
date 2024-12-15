package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.TourDestinations;
import com.uetravel.services.TourDestinationsServices;

@RestController
@RequestMapping("/tourDestinations")
public class TourDestinationsController {

    @Autowired
    private TourDestinationsServices tourDestinationsServices;

    @GetMapping
    public List<TourDestinations> getAllTourDestinations() {
        return tourDestinationsServices.getAllTourDestinations();
    }

    @GetMapping("/tourName/{tourName}")
    public ResponseEntity<List<TourDestinations>> getTourDestinationsByTourName(@PathVariable String tourName) {
        List<TourDestinations> tourDestinations = tourDestinationsServices.getTourDestinationsByTourName(tourName);
        return tourDestinations.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(tourDestinations);
    }

    @GetMapping("/destinationName/{destinationName}")
    public ResponseEntity<List<TourDestinations>> getTourDestinationsByDestinationName(@PathVariable String destinationName) {
        List<TourDestinations> tourDestinations = tourDestinationsServices.getTourDestinationsByDestinationName(destinationName);
        return tourDestinations.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(tourDestinations);
    }

    @DeleteMapping("/{tourId}/{destinationId}")
    public ResponseEntity<Void> deleteTourDestination(
            @PathVariable Integer tourId,
            @PathVariable Integer destinationId) {
        try {
            tourDestinationsServices.deleteTourDestination(tourId, destinationId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}