package com.uetravel.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

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
    public static class TourDestinationId implements Serializable {
        private Integer tourId;
        private Integer destinationId;
    }
}