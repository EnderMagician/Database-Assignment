package com.uetravel.controllers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.Payments;
import com.uetravel.services.PaymentsServices;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentsServices paymentsServices;

    @GetMapping
    public List<Payments> getAllPayments() {
        return paymentsServices.getAllPayments();
    }

    @GetMapping("/paymentDate/{paymentDate}")
    public ResponseEntity<List<Payments>> getPaymentByPaymentDate(@PathVariable Date paymentDate) {
        List<Payments> payments = paymentsServices.getPaymentByPaymentDate(paymentDate);
        return payments.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(payments);
    }

    @GetMapping("/amountRange")
    public ResponseEntity<List<Payments>> getPaymentByAmountRange(
            @RequestParam("minAmount") BigDecimal minAmount,
            @RequestParam("maxAmount") BigDecimal maxAmount) {
        List<Payments> payments = paymentsServices.getPaymentByAmountRange(minAmount, maxAmount);
        return payments.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(payments);
    }

    @GetMapping("/paymentMethod/{paymentMethod}")
    public ResponseEntity<List<Payments>> getPaymentByPaymentMethod(@PathVariable String paymentMethod) {
        List<Payments> payments = paymentsServices.getPaymentByPaymentMethod(paymentMethod);
        return payments.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(payments);
    }

    @GetMapping("/customerName/{customerName}")
    public ResponseEntity<List<Payments>> getPaymentByCustomerName(@PathVariable String customerName) {
        List<Payments> payments = paymentsServices.getPaymentByCustomerName(customerName);
        return payments.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(payments);
    }
}