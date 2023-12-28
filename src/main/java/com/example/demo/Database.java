package com.example.demo;

import com.example.demo.model.*;
import java.util.Random;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public Database() {
        Customer customer1 = new Customer("Hend", 19, 1, "ehend3343@gmail.com", 1000, "pa15oss",1);
        Customer customer2 = new Customer("Ali", 20, 2, "aa56@mail.com", 2000, "lkk8511",2);
        Customer customer3 = new Customer("Omar", 21, 3, "oa78@mail.com", 3000, "mbgg2514",3);
        Customer customer4 = new Customer("Soha", 22, 4, "skk69@mail.com", 4000, "kill8511",4);
        customers.put(customer1.getId(), customer1);
        customers.put(customer2.getId(), customer2);
        customers.put(customer3.getId(), customer3);
        customers.put(customer4.getId(), customer4);
    }


    // Methods for Customer operations
    public static void addCustomer(Customer customer) {
        customer.getCart().setID(generate_hash());
        customer.setId(generate_hash());
        customers.put(customer.getId(), customer);
    }

    public static void removeCustomer(int customerId) {
        customers.remove(customerId);
    }

    public static Customer getCustomer(int customerId) {
        return customers.get(customerId);
    }

    // Methods for Notification operations
    public static void addNotification(notification notification) {
        notification.setID(generate_hash());
        notifications.put(notification.getID(), notification);
    }

    public static void removeNotification(int notificationId) {
        notifications.remove(notificationId);
    }

    public static notification getNotification(int notificationId) {
        return notifications.get(notificationId);
    }
    //methods for order
    public static void addOrder(Order x) {
        x.setID(generate_hash());
        Orders.put(x.getID(), x);

    }

    public static void removeOrder(int id) {
        Orders.remove(id);
    }
    public static Order getOrder(int id) {
        return Orders.get(id);
    }
    // Methods for Product operations
    public static void addProduct( Product product) {
        product.setId(generate_hash());
        products.put(product.getId(), product);
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

    public static int generate_hash() {
        Random random = new Random();
        int input=random.nextInt(0,50000);
        try {
            // Use SHA-1 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(String.valueOf(input).getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to an integer
            int result = 0;
            for (byte b : hash) {
                result = (result << 8) | (b & 0xFF);
            }

            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0; // or throw an exception, depending on your requirements
        }
    }
}
