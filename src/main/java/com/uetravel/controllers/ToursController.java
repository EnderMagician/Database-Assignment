package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.Destinations;
import com.uetravel.models.Tours;
import com.uetravel.services.ToursServices;

@RestController
@RequestMapping("/tours")
public class ToursController {

    @Autowired
    private ToursServices toursServices;

    @GetMapping
    public ModelAndView getAllTours() {
        ModelAndView modelAndView = new ModelAndView("tours"); // View name: "tours"
        List<Tours> tours = toursServices.getAllTours();
        modelAndView.addObject("tours", tours); // Attribute name: "tours"
        return modelAndView;
    }

    @GetMapping("/priceRange")
    public ModelAndView getTourByPriceRange(
            @RequestParam("minPrice") Double minPrice,
            @RequestParam("maxPrice") Double maxPrice) {
        ModelAndView modelAndView = new ModelAndView("tours");
        List<Tours> tours = toursServices.getTourByPriceRange(minPrice, maxPrice);
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }

    @GetMapping("/name/{tourName}")
    public ModelAndView getTourByName(@PathVariable String tourName) {
        ModelAndView modelAndView = new ModelAndView("tours");
        List<Tours> tours = toursServices.getTourByName(tourName);
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }

    @GetMapping("/destination/{destinationName}")
    public ModelAndView getTourByDestination(@PathVariable String destinationName) {
        ModelAndView modelAndView = new ModelAndView("tours");
        List<Tours> tours = toursServices.getTourByDestination(destinationName);
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }

    @GetMapping("/{tourId}/destinations")
    public ModelAndView getTourDestinations(@PathVariable String tourName) {
        ModelAndView modelAndView = new ModelAndView("tourDestinations"); 

        try {
            List<Destinations> destinations = toursServices.getTourDestinationsByTourName(tourName);
            modelAndView.addObject("destinations", destinations); 
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", e.getMessage()); 
        }
        return modelAndView;
    }

    @DeleteMapping("/{tourId}")
    public ModelAndView deleteTour(@PathVariable Integer tourId) {
        ModelAndView modelAndView = new ModelAndView("tours");
        try {
            toursServices.deleteTour(tourId);
            modelAndView.addObject("message", "Tour deleted successfully!");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Tour not found");
        }
        return modelAndView;
    }
}