package com.uetravel.models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Hotels")
@Getter
@Setter
public class Hotels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HotelID", nullable = false)
    private Integer hotelId;

    @Column(name = "HotelName", nullable = false, length = 100)
    private String hotelName;

    @Column(name = "Address", nullable = false, length = 100)
    private String address;

    @Column(name = "Rating")
    private Integer rating;

    @Column(name = "PricePerDay", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerDay;

    @Column(name = "RoomsAvailable", nullable = false)
    private Integer roomsAvailable;

    @Column(name = "ImageURL", length = 255)
    private String imageUrl;
}