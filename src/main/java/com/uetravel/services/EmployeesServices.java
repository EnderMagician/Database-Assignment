package com.uetravel.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.Employees;
import com.uetravel.repositories.EmployeesRepo;

@Service
public class EmployeesServices {

    @Autowired
    private EmployeesRepo employeesRepo;

    public List<Employees> getAllEmployees() {
        return employeesRepo.findAll();
    }

    public List<Employees> getEmployeeByName(String name) {
        return employeesRepo.findByName(name);
    }

    public List<Employees> getEmployeeByStartedDate(Date startedDate) {
        return employeesRepo.findByStartedDate(startedDate);
    }

    public List<Employees> getEmployeeByGender(Employees.Gender gender) {
        return employeesRepo.findByGender(gender);
    }

    public List<Employees> getEmployeeByPosition(Employees.Position position) {
        return employeesRepo.findByPosition(position);
    }

    public List<Employees> getEmployeeBySalaryRange(BigDecimal minSalary, BigDecimal maxSalary) {
        return employeesRepo.findBySalaryRange(minSalary, maxSalary);
    }

    public void deleteEmployee(Integer employeeId) {
        if (!employeesRepo.existsById(employeeId)) {
            throw new IllegalArgumentException("Employee not found with ID: " + employeeId);
        }
        employeesRepo.deleteById(employeeId);
    }
}