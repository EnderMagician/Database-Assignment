package com.uetravel.repositories;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Payments;

@Repository
public interface PaymentsRepo extends JpaRepository<Payments, Integer> {

    List<Payments> findByPaymentDate(Date paymentDate);

    @Query("SELECT p FROM Payments p WHERE p.amount BETWEEN :minAmount AND :maxAmount")
    List<Payments> findByAmountRange(@Param("minAmount") BigDecimal minAmount, @Param("maxAmount") BigDecimal maxAmount);

    List<Payments> findByPaymentMethod(String paymentMethod);

    @Query("SELECT p FROM Payments p WHERE p.customer.customerName = :customerName")
    List<Payments> findByCustomerName(@Param("customerName") String customerName);
}