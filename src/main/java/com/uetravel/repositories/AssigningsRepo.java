package com.uetravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Assignings;

@Repository
public interface AssigningsRepo extends JpaRepository<Assignings, Integer> {

    @Query("SELECT a FROM Assignings a WHERE a.driver.employeeName = :employeeName OR a.tourGuide.employeeName = :employeeName")
    Iterable<Assignings> findByEmployeeName(@Param("employeeName") String employeeName);

    @Query("SELECT a FROM Assignings a WHERE a.driver.employeeId = :employeeId OR a.tourGuide.employeeId = :employeeId")
    Iterable<Assignings> findByEmployeeId(@Param("employeeId") Integer employeeId);

    @Query("SELECT a FROM Assignings a WHERE a.vehicle.registrationNumber = :registrationNumber")
    Iterable<Assignings> findByVehicleRegistrationNumber(@Param("registrationNumber") String registrationNumber);

    @Query("SELECT a FROM Assignings a WHERE a.tour.tourName = :tourName")
    Iterable<Assignings> findByTourName(@Param("tourName") String tourName);
}