package com.example.demo.controller;

import com.example.demo.Database;
import com.example.demo.model.Catalog;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class ApplicationController {
    private final OrderService orderService = new OrderService();
    // add a new customer (in case of sign up use case)
    // added in JSON format
    @PostMapping(value = "/add")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        try {
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
                Database.displayCustomers();
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }
    // Endpoint to display the catalog products
    @GetMapping("/products")
    public ResponseEntity displayCatalogProducts() {
        Catalog catalog = new Catalog();
        if (catalog.displayProducts().isEmpty())
            return ResponseEntity.badRequest().body("Catalog is empty");
        return ResponseEntity.ok(catalog.displayProducts());
    }

    // Endpoint to add products to cart and place a simple order
    @PostMapping(value = "simpleOrder/{customerID}")
    public ResponseEntity<String> makeSimpleOrder(
            @RequestBody List<Product> productInfoList,
            @PathVariable int customerID
    ) {
        try {
            // Check if the customer exists
            Customer customer = Database.getCustomer(customerID);
            if (customer == null) {
                return ResponseEntity.badRequest().body("Customer not found");
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
            boolean orderPlaced = orderService.makeSimple(customerID);

            if (orderPlaced) {
                // print the notification list of the customer
                if(customer.getNotifications() == null) {
                    System.out.println("No notifications");
                }
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
    @PostMapping(value = "compoundOrder")
    public ResponseEntity<String> makeCompoundOrder(
            @RequestBody List<Order> orders
    ) {
        List<Integer> customerIDs = new ArrayList<>();
        // Check if the customer exists
        for (Order order : orders) {
            int customerID = order.getOrder_owner();
            Customer customer = Database.getCustomer(customerID);
            if (customer == null) {
                customerIDs.add(customerID);
                return ResponseEntity.badRequest().body("Customer with ID: " + customerID + " not found");
            }
        }
        // Check if the cart belongs to the customer
        for (Order order : orders) {
            int customerID = order.getOrder_owner();
            int cartID = order.getCart().getId();
            Customer customer = Database.getCustomer(customerID);
            if (customer.getCart().getId() != cartID) {
                return ResponseEntity.badRequest().body("Cart with ID: " + cartID + " doesn't belong to the customer with ID: " + customerID);
            }
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
        boolean orderPlaced = orderService.compoundOrder(customerIDs);
        if(orderPlaced){
            return ResponseEntity.ok("Compound order placed successfully");
        }
        else{
            return ResponseEntity.badRequest().body("Failed to place the order");
        }
    }
}
