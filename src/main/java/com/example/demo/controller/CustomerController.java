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
    // added in JSON format
    @PostMapping(value = "/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            Database.addCustomer(customer);
            return ResponseEntity.ok().body(customer); // Return the created Customer object
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Return null if the customer is null
        }
    }


    // Retrieve customer data by id
    @GetMapping("/get/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
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
        Customer customer = Database.getCustomer(customerID);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            if (customer.getPassword().equals(password)) {
                customer.setLogged_in(true);
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
}
