package com.uetravel.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Bookings;

@Repository
public interface BookingsRepo extends JpaRepository<Bookings, Integer> {
    @Query("SELECT b FROM Bookings b WHERE b.customer.customerName = :customerName")
    Iterable<Bookings> findByCustomerName(@Param("customerName") String customerName);

    @Query("SELECT b FROM Bookings b WHERE b.tour.tourName = :tourName")
    Iterable<Bookings> findByTourName(@Param("tourName") String tourName);

    Iterable<Bookings> findByPickupAddress(String pickupAddress);

    Iterable<Bookings> findByStatus(Bookings.Status status);

    Iterable<Bookings> findByBookingDate(Timestamp bookingDate);
}