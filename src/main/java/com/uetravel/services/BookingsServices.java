package com.uetravel.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.Bookings;
import com.uetravel.repositories.BookingsRepo;

@Service
public class BookingsServices {
    @Autowired
    private BookingsRepo bookingsRepo;

    public List<Bookings> getAllBookings() {
        return bookingsRepo.findAll();
    }

    public List<Bookings> getBookingsByCustomerName(String customerName) {
        return bookingsRepo.findByCustomerName(customerName);
    }

    public List<Bookings> getBookingsByTourName(String tourName) {
        return bookingsRepo.findByTourName(tourName);
    }

    public List<Bookings> getBookingsByPickupAddress(String pickupAddress) {
        return bookingsRepo.findByPickupAddress(pickupAddress);
    }

    public List<Bookings> getBookingsByStatus(Bookings.Status status) {
        return bookingsRepo.findByStatus(status);
    }

    public List<Bookings> getBookingsByBookingDate(Timestamp bookingDate) {
        return bookingsRepo.findByBookingDate(bookingDate);
    }

    public Bookings updateBookingStatus(Integer bookingId, Bookings.Status newStatus) {
        return bookingsRepo.findById(bookingId)
                .map(booking -> {
                    booking.setStatus(newStatus);
                    return bookingsRepo.save(booking);
                })
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + bookingId));
    }
}