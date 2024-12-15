package com.uetravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.TourDestinations;

@Repository
public interface TourDestinationsRepo extends JpaRepository<TourDestinations, TourDestinations.TourDestinationId> {

    @Query("SELECT td FROM TourDestinations td WHERE td.tour.tourName = :tourName")
    List<TourDestinations> findByTourName(@Param("tourName") String tourName);

    @Query("SELECT td FROM TourDestinations td WHERE td.destination.destinationName = :destinationName")
    List<TourDestinations> findByDestinationName(@Param("destinationName") String destinationName);
}