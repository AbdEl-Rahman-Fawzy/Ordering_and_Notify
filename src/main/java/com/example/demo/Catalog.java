package com.example.demo;

import com.example.demo.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import java.util.HashMap;

public class Catalog {
    private static Map<Product, Integer> catalog;

    public  Catalog() {
        catalog = new HashMap<>();
        // Add some products to the catalog
        Product p1 = new Product(1, "Bread", "123", "Bakery", "Bread", 5);
        Product p2 = new Product(2, "Milk", "456", "Dairy", "Milk", 10);
        Product p3 = new Product(3, "Eggs", "789", "Dairy", "Eggs", 15);
        Product p4 = new Product(4, "Butter", "101", "Dairy", "Butter", 20);
        Product p5 = new Product(5, "Cheese", "112", "Dairy", "Cheese", 25);
        Product p6 = new Product(6, "Yogurt", "131", "Dairy", "Yogurt", 30);
        Product p7 = new Product(7, "Apple", "415", "Fruits", "Apple", 35);
        catalog.put(p1, 10);
        catalog.put(p2, 20);
        catalog.put(p3, 30);
        catalog.put(p4, 40);
        catalog.put(p5, 50);
        catalog.put(p6, 60);
        catalog.put(p7, 70);
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
                if (amount <= currentAmount) {
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

    public Map<Product, Integer> displayProducts() {
        return catalog;
    }
    public static Product getProductByID(int productID){
        for (Map.Entry<Product, Integer> entry : catalog.entrySet()) {
            if (entry.getKey().getId() == productID) {
                return entry.getKey();
            }
        }
        return null;
    }
}
