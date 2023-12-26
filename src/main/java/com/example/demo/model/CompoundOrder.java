package com.example.demo.model;

import java.util.List;

public class CompoundOrder extends Order {
    private List<SimpleOrder> orders;

    public CompoundOrder(int x , String date, Cart cart, List<SimpleOrder> orders) {
        super(x, date, cart);
        this.orders = orders;
    }

    public List<SimpleOrder> getOrders() {
        return orders;
    }

    public void addOrder(SimpleOrder order) {
        orders.add(order);
    }

    @Override
    public void calculate_total_cost() {
        double x = 0 ;
        for (Order i : orders){
            x += i.getTotalCost();
        }
        this.setTotalCost(x);
    }
}
