package com.example.demo.model;

import com.example.demo.Database;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id = -1;
    private int total_cost;

    // Properties
    private List<Product> items;

    // Constructors
    public Cart(int id ) {
        this.items = new ArrayList<>();
        this.id = id;
    }



    public String getState() {
        if (items.isEmpty()) {
            return "cart is empty !";
        }
        return "u have " + Integer.toHexString(this.items.size()) + " items in the cart";
    }
    public boolean isempty(){
        if(items.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean addItem(Product x) {
        if (x != null) {
            items.add(x);
            calc_total_cost();
            return true; // Return true if the product was added successfully
        }
        return false; // Return false if the product is null
    }


    public boolean deleteItem(int id) {
        for (Product product : items) {
            if (product.getId() == id) {
                items.remove(product); // Remove the product from the list
                calc_total_cost();
                return true; // Successfully deleted
            }
        }
        return false; // Product with the specified id not found
    }

    public void clear() {
        items.clear();
        calc_total_cost();
    }

    public String getData() {
        StringBuilder data = new StringBuilder();
        data.append(getState());
        int i = 1;
        for (Product x : items) {
            data.append("item number : ").append(Integer.toHexString(i)).append("data is\n");
            data.append(x.toString());
        }
        data.append("these are cart data");
        return String.valueOf(data);
    }

    public int getId() {
        return id;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }
    public void setID(int id){
        this.id=id;
    }
    public void calc_total_cost() {
        int temp = 0;
        for (Product x : items) {
            temp += x.getPrice();
        }
        total_cost = temp;
    }
    public List<Product> getItems() {
        return items;
    }
}
