package com.example.demo.model;

public abstract class Order {
    private String date;
    private double totalCost;
    private Cart cart;
    private int ID;
    private int order_owner;

    public Order(int x ,String date, Cart cart) {
        order_owner = x;
        this.date = date;
        this.cart = cart;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void calculateTotalCost() {}
    public void getData(){}

    public abstract void calculate_total_cost();

    public Cart getCart() {
        return cart;
    }

    public int getOrder_owner() {
        return order_owner;
    }

    public void setOrder_owner(int order_owner) {
        this.order_owner = order_owner;
    }
    public void setTotalCost (double x){
        totalCost= x;
    }
    public double getTotalCost (int x){
        return totalCost;
    }public String getDate(){
        return date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}


