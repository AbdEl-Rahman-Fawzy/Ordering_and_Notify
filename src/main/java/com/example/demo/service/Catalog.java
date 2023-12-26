package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.Map;

import java.util.HashMap;

public class Catalog {
    private static Map<Product, Integer> catalog;

    public  Catalog() {
        this.catalog = new HashMap<>();
    }

    public static int get_product_amount(int productID) {
        for (Map.Entry<Product, Integer> entry : catalog.entrySet()) {
            if (entry.getKey().getId() == productID) {
                return entry.getValue();
            }
        }
        return -1;
    }

    public static void add_item(Product product, int amount) {
        // If the product is already in the catalog, update the quantity
        if (catalog.containsKey(product)) {
            int currentAmount = catalog.get(product);
            catalog.put(product, currentAmount + amount);
        } else {
            // Otherwise, add the product to the catalog with the specified quantity
            catalog.put(product, amount);
        }
    }

    public static void remove_item(int productID, int amount) {
        for (Map.Entry<Product, Integer> entry : catalog.entrySet()) {
            if (entry.getKey().getId() == productID) {
                int currentAmount = entry.getValue();
                if (amount >= currentAmount) {
                    // If the requested amount is greater or equal to the current amount, remove the product
                    catalog.remove(entry.getKey());
                } else {
                    // Otherwise, update the quantity
                    entry.setValue(currentAmount - amount);
                }
                break;
            }
        }
    }
    public static boolean isexist(Product prod){
        return catalog.containsKey(prod);
    }

    public static void delete_item(int productID) {
        // Remove the product with the specified ID from the catalog
        catalog.entrySet().removeIf(entry -> entry.getKey().getId() == productID);
    }
}
