package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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