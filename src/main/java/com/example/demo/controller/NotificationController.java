package com.example.demo.controller;

import com.example.demo.Database;
import com.example.demo.model.Customer;
import com.example.demo.model.notification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class NotificationController {

    // Endpoint to get customer notifications
    @GetMapping("/notifications/{customerID}")
    public ResponseEntity<ArrayList<notification>> getCustomerNotifications(@PathVariable int customerID) {

        Customer customer = Database.getCustomer(customerID);
        if (customer == null) {
            return ResponseEntity.badRequest().body(null);
        }
        if(!customer.isLogged_in()) {
            return ResponseEntity.badRequest().body(null);
        }
        if(customer.getNotifications().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(customer.getNotifications());
    }
}
