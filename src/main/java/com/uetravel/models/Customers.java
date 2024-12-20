package com.uetravel.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID", nullable = false)
    private Integer customerId;

    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "CustomerName", nullable = false, length = 50)
    private String customerName;

    @Column(name = "Birthday", nullable = false)
    private Date birthday;

    public enum Gender {
        Male, Female, Other
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false)
    private Gender gender;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "PhoneNumber", nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(name = "Address", nullable = false, length = 255)
    private String address;
    @Override
    public String toString() {
        return "Customers{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
