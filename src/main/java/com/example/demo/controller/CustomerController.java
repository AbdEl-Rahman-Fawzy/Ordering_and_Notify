package com.example.demo.controller;

import com.example.demo.Database;
import com.example.demo.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    // add a new customer (in case of sign up use case)
    @PostMapping(value = "/add")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        try {
            Database.init();
            Database.addCustomer(customer);
            System.out.println(customer.getId());
            return ResponseEntity.ok("Customer added Successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add Customer" + e.getMessage());
        }
    }

    // Retrieve customer data by id
    @GetMapping("/get/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
        Database.init();
        Customer customer = Database.getCustomer(customerId);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
    // Customer login with id and password
    @GetMapping(value = "/login/{customerID}/{password}")
    public ResponseEntity<Customer> login(@PathVariable int customerID, @PathVariable String password) {
        Database.init();
        Customer customer = Database.getCustomer(customerID);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            if (customer.getPassword().equals(password)) {
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
}
