package com.uetravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Customers;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer> {
    @Query("SELECT c FROM Customers c WHERE c.customerName LIKE %:name%")
    Iterable<Customers> findByName(@Param("name") String name);

    @Query("SELECT c FROM Customers c WHERE c.gender = :gender")
    Iterable<Customers> findByGender(@Param("gender") Customers.Gender gender);

    @Query("SELECT c FROM Customers c WHERE c.address LIKE %:address%")
    Iterable<Customers> findByAddress(@Param("address") String address);
}