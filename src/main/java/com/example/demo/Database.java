package com.example.demo;

import com.example.demo.model.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    public static Map<Integer, Customer> customers = new HashMap<>();
    private static Map<Integer, notification> notifications = new HashMap<>();
    private static Map<Integer, Product> products = new HashMap<>();
    private static Map<Integer, Order> Orders = new HashMap<>();
    private static List<String> logs = new ArrayList<>();

    // Methods for Customer operations
    public static void addCustomer(int customerId, Customer customer) {
        customers.put(customerId, customer);
    }

    public static void removeCustomer(int customerId) {
        customers.remove(customerId);
    }

    public static Customer getCustomer(int customerId) {
        return customers.get(customerId);
    }

    // Methods for Notification operations
    public static void addNotification(int notificationId, notification notification) {
        notifications.put(notificationId, notification);
    }

    public static void removeNotification(int notificationId) {
        notifications.remove(notificationId);
    }

    public static notification getNotification(int notificationId) {
        return notifications.get(notificationId);
    }
    //methods for order
    public static void addOrder(int id, Order x) {
        Orders.put(id, x);
    }

    public static void removeOrder(int id) {
        Orders.remove(id);
    }
    public static Order getOrder(int id) {
        return Orders.get(id);
    }
    // Methods for Product operations
    public static void addProduct(int productId, Product product) {
        products.put(productId, product);
    }

    public static void removeProduct(int productId) {
        products.remove(productId);
    }

    public static Product getProduct(int productId) {
        return products.get(productId);
    }

    // Methods for Log operations
    public static void addLog(String log) {
        logs.add(log);
    }

    public static List<String> getLogs() {
        return new ArrayList<>(logs);
    }
    public static void displayCustomers() {
        System.out.println("Customers:");
        for (Map.Entry<Integer, Customer> entry : customers.entrySet()) {
            System.out.println("Customer ID: " + entry.getKey() + ", Name: " + entry.getValue().getName());
            // Add more details as needed
        }
        System.out.println();
    }

    // Display all notifications
    public static void displayNotifications() {
        System.out.println("Notifications:");
        for (Map.Entry<Integer, notification> entry : notifications.entrySet()) {
            System.out.println("Notification ID: " + entry.getKey() + ", Message: " + entry.getValue().getContent());
            // Add more details as needed
        }
        System.out.println();
    }

    // Display all products
    public static void displayProducts() {
        System.out.println("Products:");
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            System.out.println("Product ID: " + entry.getKey() + ", Name: " + entry.getValue().getName());
            // Add more details as needed
        }
        System.out.println();
    }
    // Display all Orders
    public static void displayOrders() {
        System.out.println("Orders:");
        for (Map.Entry<Integer, Order> entry : Orders.entrySet()) {
            entry.getValue().getData();
            // Add more details as needed
        }
        System.out.println();
    }

    public static String generateSHA1Hash(int input) {
        try {
            // Use SHA-1 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(String.valueOf(input).getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
