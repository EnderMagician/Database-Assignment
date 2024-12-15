package com.uetravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Assignings;

@Repository
public interface AssigningsRepo extends JpaRepository<Assignings, Integer> {

    @Query("SELECT a FROM Assignings a WHERE a.driver.employeeName = :employeeName OR a.tourGuide.employeeName = :employeeName")
    List<Assignings> findByEmployeeName(@Param("employeeName") String employeeName);

    @Query("SELECT a FROM Assignings a WHERE a.driver.employeeId = :employeeId OR a.tourGuide.employeeId = :employeeId")
    List<Assignings> findByEmployeeId(@Param("employeeId") Integer employeeId);

    @Query("SELECT a FROM Assignings a WHERE a.vehicle.registrationNumber = :registrationNumber")
    List<Assignings> findByVehicleRegistrationNumber(@Param("registrationNumber") String registrationNumber);

    @Query("SELECT a FROM Assignings a WHERE a.tour.tourName = :tourName")
    List<Assignings> findByTourName(@Param("tourName") String tourName);
}