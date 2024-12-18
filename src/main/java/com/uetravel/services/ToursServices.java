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
    private TourDestinationsServices tourDestinationsServices;

    public List<Tours> getAllTours() {
        return toursRepo.findAll();
    }

    public void deleteTour(Integer tourId) {
        if (!toursRepo.existsById(tourId)) {
            throw new IllegalArgumentException("Tour not found with ID: " + tourId);
        }
        toursRepo.deleteById(tourId);
    }
}