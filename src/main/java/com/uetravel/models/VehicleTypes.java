package com.uetravel.models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VehicleTypes")
@Getter
@Setter
public class VehicleTypes {
    @Id
    @Column(name = "Type", nullable = false, length = 50)
    private String type;

    @Column(name = "Seats", nullable = false)
    private Integer seats;

    @Column(name = "Lifespan", nullable = false)
    private Integer lifespan;

    @Column(name = "CostPer100km", nullable = false, precision = 10, scale = 2)
    private BigDecimal costPer100km;
}