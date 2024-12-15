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

    public Iterable<Tours> getTourByPriceRange(Double minPrice, Double maxPrice) {
        return toursRepo.findByPriceRange(minPrice, maxPrice);
    }

    public Iterable<Tours> getTourByName(String tourName) {
        return toursRepo.findByName(tourName);
    }

    public Iterable<Tours> getTourByDestination(String destinationName) {
        return toursRepo.findByDestination(destinationName);
    }
}