package com.example.demo.controller;

import com.example.demo.Database;
import com.example.demo.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {

    // Endpoint to get customer notifications
    @GetMapping("/notifications/{customerID}")
    public ResponseEntity<String> getCustomerNotifications(@PathVariable int customerID) {
        Customer customer = Database.getCustomer(customerID);
        if (customer == null) {
            return ResponseEntity.badRequest().body("Customer not found");
        }
        if(customer.getNotifications().isEmpty())
            return ResponseEntity.ok("No Notifications");
        return ResponseEntity.ok(customer.getNotifications().toString());
    }
}
