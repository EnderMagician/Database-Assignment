// BookingsController.java

package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.Bookings;
import com.uetravel.services.BookingsServices;

@RestController
@RequestMapping("/bookings")
public class BookingsController {

    @Autowired
    private BookingsServices bookingsServices;

    @GetMapping
    public List<Bookings> getAllBookings() {
        return bookingsServices.getAllBookings();
    }

    @GetMapping("/customerName/{customerName}")
    public ResponseEntity<List<Bookings>> getBookingsByCustomerName(@PathVariable String customerName) {
        List<Bookings> bookings = bookingsServices.getBookingsByCustomerName(customerName);
        return bookings.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(bookings);
    }

    @GetMapping("/tourName/{tourName}")
    public ResponseEntity<List<Bookings>> getBookingsByTourName(@PathVariable String tourName) {
        List<Bookings> bookings = bookingsServices.getBookingsByTourName(tourName);
        return bookings.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(bookings);
    }

    @GetMapping("/pickupAddress/{pickupAddress}")
    public ResponseEntity<List<Bookings>> getBookingsByPickupAddress(@PathVariable String pickupAddress) {
        List<Bookings> bookings = bookingsServices.getBookingsByPickupAddress(pickupAddress);
        return bookings.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(bookings);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Bookings>> getBookingsByStatus(@PathVariable Bookings.Status status) {
        List<Bookings> bookings = bookingsServices.getBookingsByStatus(status);
        return bookings.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(bookings);
    }

    @PatchMapping("/{bookingId}/status")
    public ResponseEntity<Bookings> updateBookingStatus(
            @PathVariable Integer bookingId, 
            @RequestParam("status") Bookings.Status newStatus) {

        try {
            Bookings updatedBooking = bookingsServices.updateBookingStatus(bookingId, newStatus);
            return ResponseEntity.ok(updatedBooking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}