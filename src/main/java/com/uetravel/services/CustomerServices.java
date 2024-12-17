package com.uetravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uetravel.models.Customers;
import com.uetravel.repositories.CustomerRepo;

@Service
public class CustomerServices {
    @Autowired
    private CustomerRepo customerRepo;

    public List<Customers> getAllCustomers() {
        return customerRepo.findAll();
    }

    public List<Customers> getCustomerByName(String name) {
        return customerRepo.findByName(name);
    }

    public List<Customers> getCustomerByGender(Customers.Gender gender) {
        return customerRepo.findByGender(gender);
    }

    public List<Customers> getCustomerByAddress(String address) {
        return customerRepo.findByAddress(address);
    }

    public void deleteCustomer(Integer customerId) {
        if (!customerRepo.existsById(customerId)) {
            throw new IllegalArgumentException("Customer not found with ID: " + customerId);
        }
        customerRepo.deleteById(customerId);
    }
}