package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;

public class OrderService {
    ///incomplete
    public boolean makeOrder(int user_id){
        try {

            if (Database.getCustomer(user_id) == null) {
                return false;
            }
            Customer current = Database.getCustomer(user_id);
            Cart x = current.getCart();
            if (current.getBalance() < x.getTotal_cost()) {
                current.getNotifications().add(NotificationService.getFailerOrder());
            } else {
                current.getNotifications().add(NotificationService.getOderData());
                current.getNotifications().add(NotificationService.getSuccessOrder());
                //


                x.clear();
            }

        }
        catch (Exception e){
            System.out.println("exeption in make order as " + e.getMessage());
        }
        return true;
    }

    public boolean addToCart(int user_id) {
        // Implementation for add_to_cart method
        return true;  // Placeholder return value
    }

    public boolean deleteFromCart(int user_id) {
        // Implementation for delete_from_cart method
        return true;  // Placeholder return value
    }

    public void getCartData(int user_id) {
        // Implementation for get_cart_data method
    }

    public void clearCart(int user_id) {
        // Implementation for clear_cart method
    }

}
