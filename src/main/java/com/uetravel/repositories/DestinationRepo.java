package com.uetravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Destinations;

@Repository
public interface DestinationRepo extends JpaRepository<Destinations, Integer> {

    @Query("SELECT d FROM Destinations d WHERE d.destinationName LIKE %:name%")
    Iterable<Destinations> findByName(@Param("name") String name);

    @Query("SELECT d FROM Destinations d WHERE d.address LIKE %:address%")
    Iterable<Destinations> findByAddress(@Param("address") String address);
}