package com.uetravel.models;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Payments")
@Getter
@Setter
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID", nullable = false)
    private Integer paymentId;

    @Column(name = "PaymentDate", nullable = false)
    private Date paymentDate;

    @Column(name = "Amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "PaymentMethod", nullable = false, length = 15)
    private String paymentMethod;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "CustomerID")
    private Customers customer; 
}