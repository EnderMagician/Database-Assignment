package com.uetravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.TourDestinations;
import com.uetravel.repositories.TourDestinationsRepo;

@Service
public class TourDestinationsServices {

    @Autowired
    private TourDestinationsRepo tourDestinationsRepo;

    public List<TourDestinations> getAllTourDestinations() {
        return tourDestinationsRepo.findAll();
    }

    public List<TourDestinations> getTourDestinationsByTourName(String tourName) {
        return tourDestinationsRepo.findByTourName(tourName);
    }

    public List<TourDestinations> getTourDestinationsByDestinationName(String destinationName) {
        return tourDestinationsRepo.findByDestinationName(destinationName);
    }

    public void deleteTourDestination(Integer tourId, Integer destinationId) {
        TourDestinations.TourDestinationId id = new TourDestinations.TourDestinationId();
        id.setTourId(tourId);
        id.setDestinationId(destinationId);
    
        if (!tourDestinationsRepo.existsById(id)) {
            throw new IllegalArgumentException("TourDestination not found with Tour ID: " + tourId + 
                    " and Destination ID: " + destinationId);
        }
        tourDestinationsRepo.deleteById(id);
    }
}