package com.uetravel.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "Bookings")
@Getter
@Setter
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID", nullable = false)
    private Integer bookingId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customers customer;

    @Column(name = "BookingDate", nullable = false)
    private Timestamp bookingDate; // Use Timestamp for DATETIME

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "TourID", nullable = false)
    private Tours tour;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "AssignedHotel", nullable = false)
    private Hotels assignedHotel;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "AssignedVehicle", nullable = false)
    private Vehicles AssignedVehicle;

    @Column(name = "PickupAddress", nullable = false, length = 100)
    private String pickupAddress;

    public enum Status {
        Paid, NotPaid, Canceled, Completed
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;
}