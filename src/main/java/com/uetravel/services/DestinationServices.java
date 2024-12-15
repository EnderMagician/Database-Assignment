package com.uetravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.Destinations;
import com.uetravel.repositories.DestinationsRepo;

@Service
public class DestinationServices {

    @Autowired
    private DestinationsRepo destinationsRepo;

    public List<Destinations> getAllDestinations() {
        return destinationsRepo.findAll();
    }

    public List<Destinations> getDestinationByName(String name) {
        return destinationsRepo.findByName(name);
    }

    public List<Destinations> getDestinationByAddress(String address) {
        return destinationsRepo.findByAddress(address);
    }
    
    public void deleteDestination(Integer destinationId) {
        if (!destinationsRepo.existsById(destinationId)) {
            throw new IllegalArgumentException("Destination not found with ID: " + destinationId);
        }
        destinationsRepo.deleteById(destinationId);
    }
}