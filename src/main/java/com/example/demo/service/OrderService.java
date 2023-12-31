package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderService {
    NotificationService n = new NotificationService();
    public boolean makeSimple(int user_id, LocalDate placementDate) {
        try {
            if (Database.getCustomer(user_id) == null) {
                return false;
            }
            Customer current = Database.getCustomer(user_id);
            Cart x = current.getCart();
            if (current.getBalance() < x.getTotal_cost()) {
                n.notify_failure_order(user_id);
                return true;
            } else {
                if(current.getCart().isempty()){
                    n.notify_empty_order(user_id);
                    return true;
                }
                Order new_ord = new SimpleOrder(current.getId(),placementDate,x);
                Database.addOrder(new_ord);
                current.setBalance(current.getBalance() - new_ord.getTotalCost());
                n.notify_order_data(user_id,new_ord.getID());
                n.notify_success_order(user_id,new_ord.getID());
//                x.clear();
            }
        }
        catch (Exception e){
            System.out.println("exception in make order as " + e.getMessage());
        }
        return true;
    }

    public boolean compoundOrder(List<Integer> IDs, LocalDate placementDate) {
        try {
            for(int i : IDs){
                makeSimple(i, placementDate);
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
            prod.setAmount(amount);
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

    public boolean cancelOrder(int user_id, int orderID, LocalDate currentDate) {
        Order order = Database.getOrder(orderID);
        if (order == null) {
            return false;
        }
        // if the order date minus the current date is more than 2 days
        // then the order can't be cancelled
        LocalDate orderDate = order.getDate();
        if (orderDate.isBefore(currentDate.minusDays(2))) {
            return false;
        }
        Customer x = Database.getCustomer(user_id);
        Cart cart = order.getCart();
        for(Product p : cart.getItems()){
            Catalog.add_item(p, p.getAmount());
        }
        x.setBalance(x.getBalance() + cart.getTotal_cost());
        // get the order object
        Order last_order = Database.getOrder(orderID);
        // remove the order from the database
        n.notify_cancel_order(user_id,last_order.getID());
        cart.clear();
        Database.removeOrder(x.getCart().getId());
        return true;
    }
}
