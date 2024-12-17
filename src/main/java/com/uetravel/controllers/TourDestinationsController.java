package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.TourDestinations;
import com.uetravel.services.TourDestinationsServices;

@RestController
@RequestMapping("/tourDestinations")
public class TourDestinationsController {

    @Autowired
    private TourDestinationsServices tourDestinationsServices;

    @GetMapping
    public ModelAndView getAllTourDestinations() {
        ModelAndView modelAndView = new ModelAndView("tourDestinations"); // View name: "tourDestinations"
        List<TourDestinations> tourDestinations = tourDestinationsServices.getAllTourDestinations();
        modelAndView.addObject("tourDestinations", tourDestinations); // Attribute name: "tourDestinations"
        return modelAndView;
    }

    @GetMapping("/tourName/{tourName}")
    public ModelAndView getTourDestinationsByTourName(@PathVariable String tourName) {
        ModelAndView modelAndView = new ModelAndView("tourDestinations");
        List<TourDestinations> tourDestinations = tourDestinationsServices.getTourDestinationsByTourName(tourName);
        modelAndView.addObject("tourDestinations", tourDestinations);
        return modelAndView;
    }

    @GetMapping("/destinationName/{destinationName}")
    public ModelAndView getTourDestinationsByDestinationName(@PathVariable String destinationName) {
        ModelAndView modelAndView = new ModelAndView("tourDestinations");
        List<TourDestinations> tourDestinations = tourDestinationsServices.getTourDestinationsByDestinationName(destinationName);
        modelAndView.addObject("tourDestinations", tourDestinations);
        return modelAndView;
    }

    @DeleteMapping("/{tourId}/{destinationId}")
    public ModelAndView deleteTourDestination(
            @PathVariable Integer tourId,
            @PathVariable Integer destinationId) {
        ModelAndView modelAndView = new ModelAndView("tourDestinations");
        try {
            tourDestinationsServices.deleteTourDestination(tourId, destinationId);
            modelAndView.addObject("message", "Tour Destination deleted successfully!");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Tour Destination not found");
        }
        return modelAndView;
    }
}