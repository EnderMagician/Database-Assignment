package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.Destinations;
import com.uetravel.services.DestinationServices;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationServices destinationServices;

    @GetMapping
    public ModelAndView getAllDestinations() {
        ModelAndView modelAndView = new ModelAndView("destinations"); // View name: "destinations"
        List<Destinations> destinations = destinationServices.getAllDestinations();
        modelAndView.addObject("destinations", destinations); // Attribute name: "destinations"
        return modelAndView;
    }

    @GetMapping("/name/{name}")
    public ModelAndView getDestinationByName(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("destinations");
        List<Destinations> destinations = destinationServices.getDestinationByName(name);
        modelAndView.addObject("destinations", destinations);
        return modelAndView;
    }

    @GetMapping("/address/{address}")
    public ModelAndView getDestinationByAddress(@PathVariable String address) {
        ModelAndView modelAndView = new ModelAndView("destinations");
        List<Destinations> destinations = destinationServices.getDestinationByAddress(address);
        modelAndView.addObject("destinations", destinations);
        return modelAndView;
    }

    @DeleteMapping("/{destinationId}")
    public ModelAndView deleteDestination(@PathVariable Integer destinationId) {
        ModelAndView modelAndView = new ModelAndView("destinations");
        try {
            destinationServices.deleteDestination(destinationId);
            modelAndView.addObject("message", "Destination deleted successfully!");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Destination not found");
        }
        return modelAndView;
    }
}