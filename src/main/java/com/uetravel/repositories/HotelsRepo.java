package com.uetravel.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Hotels;

@Repository
public interface HotelsRepo extends JpaRepository<Hotels, Integer> {
    @Query("SELECT h FROM Hotels h WHERE h.hotelName LIKE %:name%")
    Iterable<Hotels> findByName(@Param("name") String name);

    @Query("SELECT h FROM Hotels h WHERE h.address LIKE %:address%")
    Iterable<Hotels> findByAddress(@Param("address") String address);

    Iterable<Hotels> findByRating(Integer rating);

    @Query("SELECT h FROM Hotels h WHERE h.pricePerDay BETWEEN :minPrice AND :maxPrice")
    Iterable<Hotels> findByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);
}