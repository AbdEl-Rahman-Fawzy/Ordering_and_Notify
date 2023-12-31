package com.example.demo.controller;

import com.example.demo.Database;
import com.example.demo.model.*;
import com.example.demo.model.Catalog;
import com.example.demo.service.NotificationService;
import com.example.demo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService = new OrderService();
    // Endpoint to display the catalog products
    @GetMapping("/products")
    public ResponseEntity displayCatalogProducts() {
        if (Catalog.displayProducts().isEmpty())
        {
            return ResponseEntity.badRequest().body("Catalog is empty");
        }
        return ResponseEntity.ok(Catalog.displayProducts());
    }

    // Endpoint to add products to cart and place a simple order
    @PostMapping(value ="/simpleOrder/{customerID}/{placementDate}")
    public ResponseEntity<String> makeSimpleOrder(
            @RequestBody List<Product> productInfoList,
            @PathVariable int customerID,
            @PathVariable String placementDate
    ) {
        try {
            // convert the placementDate to local date
            LocalDate parsedPlacementDate = LocalDate.parse(placementDate);
            // Check if the customer exists
            Customer customer = Database.getCustomer(customerID);
            if (customer == null) {
                return ResponseEntity.badRequest().body("Customer not found");
            }
            if(!customer.isLogged_in()) {
                return ResponseEntity.badRequest().body("Customer not logged in");
            }
            // Check if the cart belongs to the customer
            if (customer.getCart().getId() != customer.getCart().getId()) {
                return ResponseEntity.badRequest().body("Cart doesn't belong to the customer");
            }

            // check if each product exists in the catalog
            for (Product productInfo : productInfoList) {
                int productID = productInfo.getId();
                int amount = productInfo.getAmount();

                // Add the product to the cart using OrderService
                boolean addedToCart = orderService.addToCart(customerID, productID, amount);
                // If product couldn't be added to the cart, return an error response
                if (!addedToCart) {
                    return ResponseEntity.badRequest().body("Product with ID: " + productID + " could not be added to the cart");
                }
            }

            // Create a simple order using OrderService
            boolean orderPlaced = orderService.makeSimple(customerID, parsedPlacementDate);

            if (orderPlaced) {
                return ResponseEntity.ok("Simple order placed successfully");
            }
            else {
                return ResponseEntity.badRequest().body("Failed to place the order");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    // Endpoint to add products to cart and place a compound order
    @PostMapping(value = "/compoundOrder/{placementDate}")
    public ResponseEntity<String> makeCompoundOrder(
            @RequestBody List<Order> orders,
            @PathVariable String placementDate)
    {
        LocalDate parsedPlacementDate = LocalDate.parse(placementDate);
        List<Integer> customerIDs = new ArrayList<>();
        // Check if the customer exists
        for (Order order : orders) {
            int customerID = order.getOrder_owner();
            Customer customer = Database.getCustomer(customerID);
            if (customer == null) {
                return ResponseEntity.badRequest().body("Customer with ID: " + customerID + " not found");
            }
            customerIDs.add(customerID);
        }
        // check if each product exists in the catalog
        for (Order order : orders) {
            int customerID = order.getOrder_owner();
            for (Product productInfo : order.getCart().getItems()) {
                int productID = productInfo.getId();
                int amount = productInfo.getAmount();

                // Add the product to the cart using OrderService
                boolean addedToCart = orderService.addToCart(customerID, productID, amount);

                // If product couldn't be added to the cart, return an error response
                if (!addedToCart) {
                    return ResponseEntity.badRequest().body("Product with ID: " + productID + " could not be added to the cart");
                }
            }
        }
        boolean orderPlaced = orderService.compoundOrder(customerIDs, parsedPlacementDate);
        if(orderPlaced){
            return ResponseEntity.ok("Compound order placed successfully");
        }
        else{
            return ResponseEntity.badRequest().body("Failed to place the order");
        }
    }
    
    // Endpoint to cancel an order
    @DeleteMapping(value = "/cancelOrder/{customerID}/{orderID}/{cancelDate}")
    public ResponseEntity<String> cancelOrder(
            @PathVariable int customerID
            ,@PathVariable int orderID,
            @PathVariable String cancelDate
    ) {
        try {
            // convert the cancelDate to local date
            LocalDate parsedCancelDate = LocalDate.parse(cancelDate);
            // check if the customer exists and logged in
            Customer customer = Database.getCustomer(customerID);
            if (customer == null) {
                return ResponseEntity.badRequest().body("Customer not found");
            }
            if (!customer.isLogged_in()) {
                return ResponseEntity.badRequest().body("Customer not logged in");
            }
            // check if the order exists
            Order order = Database.getOrder(orderID);
            if (order == null) {
                return ResponseEntity.badRequest().body("Order not found");
            }
            // delete the order
            if(orderService.cancelOrder(customerID, orderID, parsedCancelDate)) {
                return ResponseEntity.ok("Order cancelled successfully");
            }
            else {
                return ResponseEntity.badRequest().body("You have exceeded the cancellation period");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
