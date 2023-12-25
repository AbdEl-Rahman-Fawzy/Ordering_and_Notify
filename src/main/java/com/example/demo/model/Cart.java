package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id= -1;
    private int total_cost;

    // Properties
    private List<Product> items;

    // Constructors
    public Cart(int Id) {
        this.items = new ArrayList<>();
        id = Id;
    }
    public Cart(){};


    public String getState() {
        if(items.isEmpty()){
            return "cart is empty !";
        }
        return "u have "+Integer.toHexString(this.items.size())+" items in the cart";
    }

    // Methods
    public boolean addItem(Product x) {
        items.add(x);

        return false;
    }

    public boolean deleteItem(int id) {
        // Assuming Product has a getId() method
        for (Product product : items) {
            if (product.getId()== id) {
                items.remove(product); // Remove the product from the list
                return true; // Successfully deleted
            }
        }
        return false; // Product with the specified id not found
    }

    public void clear() {
        items.clear();
    }

    public String getData(String id)
    {
        StringBuilder data = new StringBuilder();
        data.append(getState());
        int i = 1;
        for(Product x : items){
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
}
