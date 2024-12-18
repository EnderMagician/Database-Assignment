package com.uetravel.controllers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.Employees;
import com.uetravel.services.EmployeesServices;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesServices employeesServices;

    @GetMapping
    public ModelAndView getAllEmployees() {
        ModelAndView modelAndView = new ModelAndView("employees"); // View name: "employees"
        List<Employees> employees = employeesServices.getAllEmployees();
        modelAndView.addObject("employees", employees); // Attribute name: "employees"
        return modelAndView;
    }

    @GetMapping("/name/{name}")
    public ModelAndView getEmployeeByName(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("employees");
        List<Employees> employees = employeesServices.getEmployeeByName(name);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/startedDate/{startedDate}")
    public ModelAndView getEmployeeByStartedDate(@PathVariable Date startedDate) {
        ModelAndView modelAndView = new ModelAndView("employees");
        List<Employees> employees = employeesServices.getEmployeeByStartedDate(startedDate);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/gender/{gender}")
    public ModelAndView getEmployeeByGender(@PathVariable Employees.Gender gender) {
        ModelAndView modelAndView = new ModelAndView("employees");
        List<Employees> employees = employeesServices.getEmployeeByGender(gender);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/position/{position}")
    public ModelAndView getEmployeeByPosition(@PathVariable Employees.Position position) {
        ModelAndView modelAndView = new ModelAndView("employees");
        List<Employees> employees = employeesServices.getEmployeeByPosition(position);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/salaryRange")
    public ModelAndView getEmployeeBySalaryRange(
            @RequestParam("minSalary") BigDecimal minSalary,
            @RequestParam("maxSalary") BigDecimal maxSalary) {
        ModelAndView modelAndView = new ModelAndView("employees");
        List<Employees> employees = employeesServices.getEmployeeBySalaryRange(minSalary, maxSalary);
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @DeleteMapping("/{employeeId}")
    public ModelAndView deleteEmployee(@PathVariable Integer employeeId) {
        ModelAndView modelAndView = new ModelAndView("employees");
        try {
            employeesServices.deleteEmployee(employeeId);
            modelAndView.addObject("message", "Employee deleted successfully!");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Employee not found");
        }
        return modelAndView;
    }
}