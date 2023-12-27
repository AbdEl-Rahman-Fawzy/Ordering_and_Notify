package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderService {
    NotificationService n = new NotificationService();
    public boolean makeSimple(int user_id){
        try {
            if (Database.getCustomer(user_id) == null) {
                return false;
            }
            Customer current = Database.getCustomer(user_id);
            Cart x = current.getCart();
            if (current.getBalance() < x.getTotal_cost()) {
                NotificationService.notify_failure_order(user_id);
                return true;
            } else {
                if(current.getCart().isempty()){
                    NotificationService.notify_emtpy_order(user_id);
                    return true;
                }
                String currentDate = getCurrentDate();
                Order new_ord = new SimpleOrder(current.getId(),currentDate,x);
                current.setBalance(current.getBalance() - new_ord.getTotalCost());
                NotificationService.notify_order_data(user_id,new_ord.getID());
                NotificationService.notify_success_order(user_id,new_ord.getID());
                Database.addOrder(new_ord);
                x.clear();
            }
        }
        catch (Exception e){
            System.out.println("exception in make order as " + e.getMessage());
        }
        return true;
    }

    public boolean compoundOrder(List<Integer> IDs){
        try {
            for(int i : IDs){
                makeSimple(i);
            }
        }
        catch (Exception e){
            System.out.println("exception in make order as " + e.getMessage());
        }
        return true;
    }

    public boolean addToCart(int user_id, int productID, int amount) {
        Customer current = Database.getCustomer(user_id);
        Product prod = Catalog.getProductByID(productID);

        if (prod != null) {
            boolean addedToCart = current.getCart().addItem(prod);
            if (addedToCart) {
                Catalog.remove_item(productID, amount);
                return true;
            }
        }
        return false;
    }


    public boolean deleteFromCart(int user_id , int productID,int amount) {
        Customer current= Database.getCustomer(user_id);
        Product prod=Database.getProduct(productID);
        current.getCart().deleteItem(productID);
        Catalog.add_item(prod,amount);
        return true;  // Placeholder return value
    }

    public String getCartData(int user_id) {
        Customer current=Database.getCustomer(user_id);
        return current.getCart().getData()+"     "+current.getCart().getState();
    }
    public static String getCurrentDate() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time using the specified format
        String formattedDate = now.format(formatter);

        return formattedDate;
    }

    public void clearCart(int user_id) {
        Customer x = Database.getCustomer(user_id);
        x.getCart().clear();
    }
}
