package com.example.demo.model;
import java.util.List;

public class Order {
    private String date;
    private double totalCost;
    private Cart cart;

    public Order(String date, double totalCost, Cart cart) {
        this.date = date;
        this.totalCost = totalCost;
        this.cart = cart;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void calculateTotalCost() {
        // Implementation to calculate total cost
    }
    public void getData(){

    }
}


