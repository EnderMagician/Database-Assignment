package com.uetravel.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Hotels;

@Repository
public interface HotelsRepo extends JpaRepository<Hotels, Integer> {
    @Query("SELECT h FROM Hotels h WHERE h.hotelName LIKE %:name%")
    List<Hotels> findByName(@Param("name") String name);

    @Query("SELECT h FROM Hotels h WHERE h.address LIKE %:address%")
    List<Hotels> findByAddress(@Param("address") String address);

    List<Hotels> findByRating(Integer rating);

    @Query("SELECT h FROM Hotels h WHERE h.pricePerDay BETWEEN :minPrice AND :maxPrice")
    List<Hotels> findByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);
}