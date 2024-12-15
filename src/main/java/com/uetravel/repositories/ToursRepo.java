package com.uetravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Tours;

@Repository 
public interface ToursRepo extends JpaRepository<Tours, Integer> {
    @Query("SELECT t FROM tours t WHERE t.tourName LIKE %:tourName%")
    Iterable<Tours> findByName(@Param("tourName") String tourName);

    @Query("SELECT t FROM Tours t JOIN t.tourDestinations td JOIN td.destination tdn WHERE tdn.destinationName = %:destinationName%")
    Iterable<Tours> findByDestination(@Param("destinationName") String destinationName);

    @Query("SELECT t FROM Tours t WHERE t.price BETWEEN :minPrice AND :maxPrice")
    Iterable<Tours> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice); 
}