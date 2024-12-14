package com.uetravel.models;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tours")
@Getter
@Setter
public class Tours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TourID", nullable = false)
    private Integer tourId;

    @Column(name = "TourName", nullable = false, length = 100)
    private String tourName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "StartDate", nullable = false)
    private Date startDate;

    @Column(name = "EndDate", nullable = false)
    private Date endDate;

    @Column(name = "BookingDeadline", nullable = false)
    private Date bookingDeadline;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "MaxCapacity", nullable = false)
    private Integer maxCapacity;

    public enum Status {
        Open, Closed
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private Status status;

    @Column(name = "ImageURL", length = 255)
    private String imageUrl;
}
