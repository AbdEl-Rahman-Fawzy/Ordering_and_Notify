package com.example.demo.model;

public class SimpleOrder extends Order {
    public SimpleOrder(int x , String date, double totalCost, Cart cart) {
        super(x, date, totalCost, cart);
    }

    @Override
    public void calculate_total_cost() {
        setTotalCost( getCart().getTotal_cost());
    }
}
