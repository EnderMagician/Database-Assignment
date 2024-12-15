package com.uetravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.Tours;
import com.uetravel.repositories.ToursRepo;

@Service
public class ToursServices {
    @Autowired
    private ToursRepo toursRepo;

    public List<Tours> getAllTours() {
        return toursRepo.findAll();
    }

    public List<Tours> getTourByPriceRange(Double minPrice, Double maxPrice) {
        return toursRepo.findByPriceRange(minPrice, maxPrice);
    }

    public List<Tours> getTourByName(String tourName) {
        return toursRepo.findByName(tourName);
    }

    public List<Tours> getTourByDestination(String destinationName) {
        return toursRepo.findByDestination(destinationName);
    }

    public void deleteTour(Integer tourId) {
        if (!toursRepo.existsById(tourId)) {
            throw new IllegalArgumentException("Tour not found with ID: " + tourId);
        }
        toursRepo.deleteById(tourId);
    }
}