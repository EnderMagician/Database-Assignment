package com.uetravel.repositories;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uetravel.models.Employees;

@Repository
public interface EmployeesRepo extends JpaRepository<Employees, Integer> {
    @Query("SELECT e FROM Employees e WHERE e.employeeName LIKE %:name%")
    Iterable<Employees> findByName(@Param("name") String name);

    Iterable<Employees> findByStartedDate(Date startedDate);

    Iterable<Employees> findByGender(Employees.Gender gender);

    Iterable<Employees> findByPosition(Employees.Position position);

    @Query("SELECT e FROM Employees e WHERE e.salary BETWEEN :minSalary AND :maxSalary")
    Iterable<Employees> findBySalaryRange(@Param("minSalary") BigDecimal minSalary, @Param("maxSalary") BigDecimal maxSalary);

    @Query("SELECT e FROM Employees e " +
    "JOIN e.assignings a " +
    "JOIN a.tour t " +
    "WHERE t.tourName = :tourName")
    Iterable<Employees> findByTourName(@Param("tourName") String tourName);
}