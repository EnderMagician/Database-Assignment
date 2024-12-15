package com.uetravel.controllers;

import com.uetravel.models.Customers;
import com.uetravel.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    @GetMapping
    public List<Customers> getAllCustomers() {
        return customerServices.getAllCustomers();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customers>> getCustomerByName(@PathVariable String name) {
        List<Customers> customers = customerServices.getCustomerByName(name);
        return customers.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(customers);
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<List<Customers>> getCustomerByGender(@PathVariable Customers.Gender gender) {
        List<Customers> customers = customerServices.getCustomerByGender(gender);
        return customers.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(customers);
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<List<Customers>> getCustomerByAddress(@PathVariable String address) {
        List<Customers> customers = customerServices.getCustomerByAddress(address);
        return customers.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(customers);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) {
        try {
            customerServices.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}