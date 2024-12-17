package com.uetravel.controllers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.Payments;
import com.uetravel.services.PaymentsServices;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentsServices paymentsServices;

    @GetMapping
    public ModelAndView getAllPayments() {
        ModelAndView modelAndView = new ModelAndView("payments"); // View name: "payments"
        List<Payments> payments = paymentsServices.getAllPayments();
        modelAndView.addObject("payments", payments); // Attribute name: "payments"
        return modelAndView;
    }

    @GetMapping("/paymentDate/{paymentDate}")
    public ModelAndView getPaymentByPaymentDate(@PathVariable Date paymentDate) {
        ModelAndView modelAndView = new ModelAndView("payments");
        List<Payments> payments = paymentsServices.getPaymentByPaymentDate(paymentDate);
        modelAndView.addObject("payments", payments);
        return modelAndView;
    }

    @GetMapping("/amountRange")
    public ModelAndView getPaymentByAmountRange(
            @RequestParam("minAmount") BigDecimal minAmount,
            @RequestParam("maxAmount") BigDecimal maxAmount) {
        ModelAndView modelAndView = new ModelAndView("payments");
        List<Payments> payments = paymentsServices.getPaymentByAmountRange(minAmount, maxAmount);
        modelAndView.addObject("payments", payments);
        return modelAndView;
    }

    @GetMapping("/paymentMethod/{paymentMethod}")
    public ModelAndView getPaymentByPaymentMethod(@PathVariable String paymentMethod) {
        ModelAndView modelAndView = new ModelAndView("payments");
        List<Payments> payments = paymentsServices.getPaymentByPaymentMethod(paymentMethod);
        modelAndView.addObject("payments", payments);
        return modelAndView;
    }

    @GetMapping("/customerName/{customerName}")
    public ModelAndView getPaymentByCustomerName(@PathVariable String customerName) {
        ModelAndView modelAndView = new ModelAndView("payments");
        List<Payments> payments = paymentsServices.getPaymentByCustomerName(customerName);
        modelAndView.addObject("payments", payments);
        return modelAndView;
    }
}