package com.example.demo.model;

public class SimpleOrder extends Order {
    public SimpleOrder(int x , String date, Cart cart) {
        super(x, date, cart);
    }

    @Override
    public void calculate_total_cost() {
        setTotalCost( getCart().getTotal_cost());
    }
}
