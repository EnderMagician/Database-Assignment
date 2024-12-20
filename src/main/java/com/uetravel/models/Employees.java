package com.uetravel.models;

import java.math.BigDecimal;
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
@Table(name = "employees")
@Getter
@Setter
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID", nullable = false)
    private Integer employeeId;

    @Column(name = "EmployeeName", nullable = false, length = 50)
    private String employeeName;

    @Column(name = "Birthday", nullable = false)
    private Date birthday;

    @Column(name = "Email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "PhoneNumber", nullable = false, length = 10, unique = true)
    private String phoneNumber;

    public enum Gender {
        Male, Female, Other
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Gender", nullable = false)
    private Gender gender;

    public enum Position {
        Driver, TourGuide 
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "Position", nullable = false)
    private Position position;

    @Column(name = "StartedDate", nullable = false)
    private Date startedDate;

    @Column(name = "Salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;
}
