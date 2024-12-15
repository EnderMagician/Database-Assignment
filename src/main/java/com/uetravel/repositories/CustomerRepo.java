package com.uetravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Customers;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer> {
    @Query("SELECT c FROM Customers c WHERE c.customerName LIKE %:name%")
    List<Customers> findByName(@Param("name") String name);

    @Query("SELECT c FROM Customers c WHERE c.gender = :gender")
    List<Customers> findByGender(@Param("gender") Customers.Gender gender);

    @Query("SELECT c FROM Customers c WHERE c.address LIKE %:address%")
    List<Customers> findByAddress(@Param("address") String address);
}