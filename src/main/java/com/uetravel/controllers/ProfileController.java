package com.uetravel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class ProfileController {
    private CustomerController customerController;
    private BookingsController bookingsController;
    private PaymentsController paymentsController;

    @GetMapping()
    public ModelAndView getProfile() {
        return customerController.getCustomerByName("User0");
    }

    @GetMapping()
    public ModelAndView getBookings() {
        return bookingsController.getBookingsByCustomerName("User0");
    }

    @GetMapping()
    public ModelAndView getPayments() {
        return paymentsController.getPaymentByCustomerName("User0");
    }
}
