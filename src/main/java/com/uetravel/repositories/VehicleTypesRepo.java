package com.uetravel.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.VehicleTypes;

@Repository
public interface VehicleTypesRepo extends JpaRepository<VehicleTypes, String> {
    VehicleTypes findByType(String type);

    @Query("SELECT vt FROM VehicleTypes vt WHERE vt.costPer100km BETWEEN :minCost AND :maxCost")
    List<VehicleTypes> findByCostRange(@Param("minCost") BigDecimal minCost, @Param("maxCost") BigDecimal maxCost, Sort sort);

    List<VehicleTypes> findBySeats(Integer seats);
}