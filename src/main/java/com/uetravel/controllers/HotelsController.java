package com.uetravel.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.Hotels;
import com.uetravel.services.HotelsServices;

@RestController
@RequestMapping("/hotels")
public class HotelsController {

    @Autowired
    private HotelsServices hotelsServices;

    @GetMapping
    public List<Hotels> getAllHotels() {
        return hotelsServices.getAllHotels();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Hotels>> getHotelByName(@PathVariable String name) {
        List<Hotels> hotels = hotelsServices.getHotelByName(name);
        return hotels.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(hotels);
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<List<Hotels>> getHotelByAddress(@PathVariable String address) {
        List<Hotels> hotels = hotelsServices.getHotelByAddress(address);
        return hotels.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(hotels);
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Hotels>> getHotelByRating(@PathVariable Integer rating) {
        List<Hotels> hotels = hotelsServices.getHotelByRating(rating);
        return hotels.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(hotels);
    }

    @GetMapping("/priceRange")
    public ResponseEntity<List<Hotels>> getHotelByPriceRange(
            @RequestParam("minPrice") BigDecimal minPrice,
            @RequestParam("maxPrice") BigDecimal maxPrice) {
        List<Hotels> hotels = hotelsServices.getHotelByPriceRange(minPrice, maxPrice);
        return hotels.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(hotels);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Integer hotelId) {
        try {
            hotelsServices.deleteHotel(hotelId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}