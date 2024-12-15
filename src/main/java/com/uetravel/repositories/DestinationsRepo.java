package com.uetravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Destinations;

@Repository
public interface DestinationsRepo extends JpaRepository<Destinations, Integer> {

    @Query("SELECT d FROM Destinations d WHERE d.destinationName LIKE %:name%")
    List<Destinations> findByName(@Param("name") String name);

    @Query("SELECT d FROM Destinations d WHERE d.address LIKE %:address%")
    List<Destinations> findByAddress(@Param("address") String address);
}