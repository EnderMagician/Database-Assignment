package com.uetravel.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.Hotels;
import com.uetravel.services.HotelsServices;

@RestController
@RequestMapping("/hotels")
public class HotelsController {

    @Autowired
    private HotelsServices hotelsServices;

    @GetMapping
    public ModelAndView getAllHotels() {
        ModelAndView modelAndView = new ModelAndView("hotels"); // View name: "hotels"
        List<Hotels> hotels = hotelsServices.getAllHotels();
        modelAndView.addObject("hotels", hotels); // Attribute name: "hotels"
        return modelAndView;
    }

    @GetMapping("/name/{name}")
    public ModelAndView getHotelByName(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("hotels");
        List<Hotels> hotels = hotelsServices.getHotelByName(name);
        modelAndView.addObject("hotels", hotels);
        return modelAndView;
    }

    @GetMapping("/address/{address}")
    public ModelAndView getHotelByAddress(@PathVariable String address) {
        ModelAndView modelAndView = new ModelAndView("hotels");
        List<Hotels> hotels = hotelsServices.getHotelByAddress(address);
        modelAndView.addObject("hotels", hotels);
        return modelAndView;
    }

    @GetMapping("/rating/{rating}")
    public ModelAndView getHotelByRating(@PathVariable Integer rating) {
        ModelAndView modelAndView = new ModelAndView("hotels");
        List<Hotels> hotels = hotelsServices.getHotelByRating(rating);
        modelAndView.addObject("hotels", hotels);
        return modelAndView;
    }

    @GetMapping("/priceRange")
    public ModelAndView getHotelByPriceRange(
            @RequestParam("minPrice") BigDecimal minPrice,
            @RequestParam("maxPrice") BigDecimal maxPrice) {
        ModelAndView modelAndView = new ModelAndView("hotels");
        List<Hotels> hotels = hotelsServices.getHotelByPriceRange(minPrice, maxPrice);
        modelAndView.addObject("hotels", hotels);
        return modelAndView;
    }

    @DeleteMapping("/{hotelId}")
    public ModelAndView deleteHotel(@PathVariable Integer hotelId) {
        ModelAndView modelAndView = new ModelAndView("hotels");
        try {
            hotelsServices.deleteHotel(hotelId);
            modelAndView.addObject("message", "Hotel deleted successfully!");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Hotel not found");
        }
        return modelAndView;
    }
}