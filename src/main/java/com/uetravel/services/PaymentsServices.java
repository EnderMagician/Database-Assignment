package com.uetravel.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.Payments;
import com.uetravel.repositories.PaymentsRepo;

@Service
public class PaymentsServices {

    @Autowired
    private PaymentsRepo paymentsRepo;

    public List<Payments> getAllPayments() {
        return paymentsRepo.findAll();
    }

    public List<Payments> getPaymentByPaymentDate(Date paymentDate) {
        return paymentsRepo.findByPaymentDate(paymentDate);
    }

    public List<Payments> getPaymentByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        return paymentsRepo.findByAmountRange(minAmount, maxAmount);
    }

    public List<Payments> getPaymentByPaymentMethod(String paymentMethod) {
        return paymentsRepo.findByPaymentMethod(paymentMethod);
    }

    public List<Payments> getPaymentByCustomerName(String customerName) {
        return paymentsRepo.findByCustomerName(customerName);
    }
}