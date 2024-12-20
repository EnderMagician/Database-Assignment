package com.uetravel.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Vehicles;

@Repository
public interface VehiclesRepo extends JpaRepository<Vehicles, String> {
    Vehicles findByRegistrationNumber(String registrationNumber);

    List<Vehicles> findByPurchasedDate(Date purchasedDate);
}