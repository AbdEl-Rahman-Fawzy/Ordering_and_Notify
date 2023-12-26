package com.example.demo.controller;

import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    // make a new simple order
    @PostMapping(value = "simpleOrder")
    public ResponseEntity<String> makeSimpleOrder(@RequestParam int userID) {
        boolean success = orderService.makeSimple(userID);
        if (success) {
            return ResponseEntity.ok("Simple Order made Successfully");
        }
        return ResponseEntity.badRequest().body("Failed to make Simple Order");
    }

}
