package com.uetravel.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "Assignings")
@Getter
@Setter
public class Assignings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AssigningID", nullable = false)
    private Integer assigningId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "DriverID", nullable = false)
    private Employees driver;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "TourGuideID")
    private Employees tourGuide;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "Vehicle", nullable = false)
    private Vehicles vehicle;

    @Column(name = "StartTime", nullable = false)
    private Timestamp startTime;

    @Column(name = "EndTime", nullable = false)
    private Timestamp endTime;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "StartDestination", nullable = false)
    private Destinations startDestination;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "EndDestination", nullable = false)
    private Destinations endDestination;
}