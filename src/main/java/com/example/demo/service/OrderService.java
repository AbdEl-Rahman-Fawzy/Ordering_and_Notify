package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderService {
    ///incomplete
    public boolean makeSimple(int user_id){
        try {

            if (Database.getCustomer(user_id) == null) {
                return false;
            }
            Customer current = Database.getCustomer(user_id);
            Cart x = current.getCart();
            if (current.getBalance() < x.getTotal_cost()) {
                current.getNotifications().add(NotificationService.getFailureOrder(user_id));
            } else {
                if(current.getCart().isempty()){
                    current.getNotifications().add(NotificationService.emptyCartOrder(user_id));
                    return true;
                }
                String currentDate = getCurrentDate();
                Order new_ord = new SimpleOrder(current.getId(),currentDate,x);
                current.setBalance(current.getBalance()-new_ord.getTotalCost());
                current.getNotifications().add(NotificationService.getOderData(user_id,new_ord.getID()));
                current.getNotifications().add(NotificationService.getSuccessSimpleOrder(user_id,new_ord.getID()));

                Database.addOrder(new_ord);
                x.clear();
            }

        }
        catch (Exception e){
            System.out.println("exeption in make order as " + e.getMessage());
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
            System.out.println("exeption in make order as " + e.getMessage());
        }
        return true;
    }


    public boolean addToCart(int user_id,int productID,int amount) {
        Customer current=Database.getCustomer(user_id);
        Product prod=Database.getProduct(productID);
        if(Catalog.get_product_amount(productID)>=amount) {
            current.getCart().addItem(prod);
            Catalog.remove_item(productID,amount);
            return true;
        }
        else
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
        // Implementation for clear_cart method
    }

}
