package com.uetravel.models;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TourDestinations")
@Getter
@Setter
public class TourDestinations {
    @EmbeddedId
    private TourDestinationId id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("tourId")
    @JoinColumn(name = "TourID", nullable = false)
    private Tours tour;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("destinationId")
    @JoinColumn(name = "DestinationID", nullable = false)
    private Destinations destination;

    @Column(name = "ArrivalTime", nullable = false)
    private Timestamp arrivalTime;

    @Column(name = "DepartureTime", nullable = false)
    private Timestamp departureTime;

    @Embeddable
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class TourDestinationId implements Serializable {
        private Integer tourId;
        private Integer destinationId;
    }
}