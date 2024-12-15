package com.uetravel.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Bookings;

@Repository
public interface BookingsRepo extends JpaRepository<Bookings, Integer> {
    @Query("SELECT b FROM Bookings b WHERE b.customer.customerName = :customerName")
    List<Bookings> findByCustomerName(@Param("customerName") String customerName);

    @Query("SELECT b FROM Bookings b WHERE b.tour.tourName = :tourName")
    List<Bookings> findByTourName(@Param("tourName") String tourName);

    List<Bookings> findByPickupAddress(String pickupAddress);

    List<Bookings> findByStatus(Bookings.Status status);

    List<Bookings> findByBookingDate(Timestamp bookingDate);
}