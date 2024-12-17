package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.Bookings;
import com.uetravel.services.BookingsServices;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
    private BookingsServices bookingsServices;

    @GetMapping
    public ModelAndView getAllBookings() {
        ModelAndView modelAndView = new ModelAndView("bookings");
        List<Bookings> bookings = bookingsServices.getAllBookings();
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @GetMapping("/customerName/{customerName}")
    public ModelAndView getBookingsByCustomerName(@PathVariable String customerName) {
        ModelAndView modelAndView = new ModelAndView("bookings");
        List<Bookings> bookings = bookingsServices.getBookingsByCustomerName(customerName);
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @GetMapping("/tourName/{tourName}")
    public ModelAndView getBookingsByTourName(@PathVariable String tourName) {
        ModelAndView modelAndView = new ModelAndView("bookings");
        List<Bookings> bookings = bookingsServices.getBookingsByTourName(tourName);
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @GetMapping("/pickupAddress/{pickupAddress}")
    public ModelAndView getBookingsByPickupAddress(@PathVariable String pickupAddress) {
        ModelAndView modelAndView = new ModelAndView("bookings");
        List<Bookings> bookings = bookingsServices.getBookingsByPickupAddress(pickupAddress);
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @GetMapping("/status/{status}")
    public ModelAndView getBookingsByStatus(@PathVariable Bookings.Status status) {
        ModelAndView modelAndView = new ModelAndView("bookings");
        List<Bookings> bookings = bookingsServices.getBookingsByStatus(status);
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }
    
    @PatchMapping("/{bookingId}/status")
    public ModelAndView updateBookingStatus(
            @PathVariable Integer bookingId,
            @RequestParam("status") Bookings.Status newStatus) {

        ModelAndView modelAndView = new ModelAndView("bookings");
        try {
            Bookings updatedBooking = bookingsServices.updateBookingStatus(bookingId, newStatus);
            modelAndView.addObject("bookings", List.of(updatedBooking));
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Booking not found");
        }
        return modelAndView;
    }
}