package com.uetravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Tours;

@Repository 
public interface ToursRepo extends JpaRepository<Tours, Integer> {

}