package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.Tours;
import com.uetravel.services.ToursServices;

@RestController
@RequestMapping("/tours")
public class ToursController {

    @Autowired
    private ToursServices toursServices;

    @GetMapping
    public List<Tours> getAllTours() {
        return toursServices.getAllTours();
    }

    @GetMapping("/priceRange")
    public ResponseEntity<List<Tours>> getTourByPriceRange(
            @RequestParam("minPrice") Double minPrice,
            @RequestParam("maxPrice") Double maxPrice) {
        List<Tours> tours = toursServices.getTourByPriceRange(minPrice, maxPrice);
        return tours.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(tours);
    }

    @GetMapping("/name/{tourName}")
    public ResponseEntity<List<Tours>> getTourByName(@PathVariable String tourName) {
        List<Tours> tours = toursServices.getTourByName(tourName);
        return tours.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(tours);
    }

    @GetMapping("/destination/{destinationName}")
    public ResponseEntity<List<Tours>> getTourByDestination(@PathVariable String destinationName) {
        List<Tours> tours = toursServices.getTourByDestination(destinationName);
        return tours.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(tours);
    }

    @DeleteMapping("/{tourId}")
    public ResponseEntity<Void> deleteTour(@PathVariable Integer tourId) {
        try {
            toursServices.deleteTour(tourId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}