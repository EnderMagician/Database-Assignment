package com.uetravel.models;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Vehicles")
@Getter
@Setter
public class Vehicles {
    @Id
    @Column(name = "RegistrationNumber", nullable = false, length = 9)
    private String registrationNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // Adjust cascade types as needed
    @JoinColumn(name = "Type")
    private VehicleTypes type; 

    @Column(name = "PurchasedDate", nullable = false)
    private Date purchasedDate;
}