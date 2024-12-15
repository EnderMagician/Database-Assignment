package com.uetravel.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.Hotels;
import com.uetravel.repositories.HotelsRepo;

@Service
public class HotelsServices {

    @Autowired
    private HotelsRepo hotelsRepo;

    public List<Hotels> getAllHotels() {
        return hotelsRepo.findAll();
    }

    public List<Hotels> getHotelByName(String name) {
        return hotelsRepo.findByName(name);
    }

    public List<Hotels> getHotelByAddress(String address) {
        return hotelsRepo.findByAddress(address);
    }

    public List<Hotels> getHotelByRating(Integer rating) {
        return hotelsRepo.findByRating(rating);
    }

    public List<Hotels> getHotelByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return hotelsRepo.findByPriceRange(minPrice, maxPrice);
    }

    public void deleteHotel(Integer hotelId) {
        if (!hotelsRepo.existsById(hotelId)) {
            throw new IllegalArgumentException("Hotel not found with ID: " + hotelId);
        }
        hotelsRepo.deleteById(hotelId);
    }
}