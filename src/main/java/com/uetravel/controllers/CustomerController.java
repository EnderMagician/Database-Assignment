package com.uetravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.uetravel.models.Customers;
import com.uetravel.services.CustomerServices;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;

    @GetMapping
    public ModelAndView getAllCustomers() {
        ModelAndView modelAndView = new ModelAndView("customers"); // View name: "customers.html"
        List<Customers> customers = customerServices.getAllCustomers();
        System.out.println(customers.size());
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/name/{name}")
    public ModelAndView getCustomerByName(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView("customers");
        List<Customers> customers = customerServices.getCustomerByName(name);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/gender/{gender}")
    public ModelAndView getCustomerByGender(@PathVariable Customers.Gender gender) {
        ModelAndView modelAndView = new ModelAndView("customers");
        List<Customers> customers = customerServices.getCustomerByGender(gender);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/address/{address}")
    public ModelAndView getCustomerByAddress(@PathVariable String address) {
        ModelAndView modelAndView = new ModelAndView("customers");
        List<Customers> customers = customerServices.getCustomerByAddress(address);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @DeleteMapping("/{customerId}")
    public ModelAndView deleteCustomer(@PathVariable Integer customerId) {
        ModelAndView modelAndView = new ModelAndView("customers");
        try {
            customerServices.deleteCustomer(customerId);
            modelAndView.addObject("message", "Customer deleted successfully!");
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "Customer not found");
        }
        return modelAndView;
    }
}