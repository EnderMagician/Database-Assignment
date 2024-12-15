package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.Destinations;
import com.uetravel.services.DestinationServices;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationServices destinationServices;

    @GetMapping
    public List<Destinations> getAllDestinations() {
        return destinationServices.getAllDestinations();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Destinations>> getDestinationByName(@PathVariable String name) {
        List<Destinations> destinations = destinationServices.getDestinationByName(name);
        return destinations.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(destinations);
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<List<Destinations>> getDestinationByAddress(@PathVariable String address) {
        List<Destinations> destinations = destinationServices.getDestinationByAddress(address);
        return destinations.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(destinations);
    }

    @DeleteMapping("/{destinationId}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Integer destinationId) {
        try {
            destinationServices.deleteDestination(destinationId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}