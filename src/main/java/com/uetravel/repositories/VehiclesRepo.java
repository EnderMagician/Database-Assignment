package com.uetravel.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Vehicles;

@Repository
public interface VehiclesRepo extends JpaRepository<Vehicles, String> {
    Iterable<Vehicles> findByType(String type);

    Vehicles findByRegistrationNumber(String registrationNumber);

    Iterable<Vehicles> findByPurchasedDate(Date purchasedDate);
}