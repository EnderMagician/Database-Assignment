package com.uetravel.controllers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uetravel.models.Employees;
import com.uetravel.services.EmployeesServices;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesServices employeesServices;

    @GetMapping
    public List<Employees> getAllEmployees() {
        return employeesServices.getAllEmployees();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Employees>> getEmployeeByName(@PathVariable String name) {
        List<Employees> employees = employeesServices.getEmployeeByName(name);
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(employees);
    }

    @GetMapping("/startedDate/{startedDate}")
    public ResponseEntity<List<Employees>> getEmployeeByStartedDate(@PathVariable Date startedDate) {
        List<Employees> employees = employeesServices.getEmployeeByStartedDate(startedDate);
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(employees);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Employees>> getEmployeeByGender(@PathVariable Employees.Gender gender) {
        List<Employees> employees = employeesServices.getEmployeeByGender(gender);
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(employees);
    }

    @GetMapping("/position/{position}")
    public ResponseEntity<List<Employees>> getEmployeeByPosition(@PathVariable Employees.Position position) {
        List<Employees> employees = employeesServices.getEmployeeByPosition(position);
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(employees);
    }

    @GetMapping("/salaryRange")
    public ResponseEntity<List<Employees>> getEmployeeBySalaryRange(
            @RequestParam("minSalary") BigDecimal minSalary,
            @RequestParam("maxSalary") BigDecimal maxSalary) {
        List<Employees> employees = employeesServices.getEmployeeBySalaryRange(minSalary, maxSalary);
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(employees);
    }

    @GetMapping("/tourName/{tourName}")
    public ResponseEntity<List<Employees>> getEmployeeByTourName(@PathVariable String tourName) {
        List<Employees> employees = employeesServices.getEmployeeByTourName(tourName);
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(employees);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer employeeId) {
        try {
            employeesServices.deleteEmployee(employeeId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}