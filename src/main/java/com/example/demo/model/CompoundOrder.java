package com.example.demo.model;

import java.util.List;

public class CompoundOrder extends Order {
    private List<Order> orders;

    public CompoundOrder(String date, double totalCost, Cart cart, List<Order> orders) {
        super(date, totalCost, cart);
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
