package com.uetravel.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Destinations")
@Getter
@Setter
public class Destinations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DestinationID", nullable = false)
    private Integer destinationId;

    @Column(name = "DestinationName", length = 100)
    private String destinationName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "ImageURL", length = 255)
    private String imageUrl;

    @Column(name = "Address", nullable = false, length = 100)
    private String address;
}