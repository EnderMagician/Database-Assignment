package com.uetravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Tours;

@Repository 
public interface ToursRepo extends JpaRepository<Tours, Integer> {
    @Query("SELECT t FROM tours t")
    Iterable<Tours> findAllWithQuerydsl();

    @Query("SELECT t FROM tours t WHERE t.tourName LIKE %:tourName%")
    Iterable<Tours> findByName(@Param("tourName") String tourName);
}