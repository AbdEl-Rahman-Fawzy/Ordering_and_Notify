package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.*;

public class NotificationService {
    public static void notify_failure_order(int userID) {
        Customer current=Database.getCustomer(userID);
        String content = "Dear: " +current.getName() +" you dont have enough balance to make this order \n your cart contains"
                +current.getCart().getData();
        notification notf=new notification(content, NotificationType.FAILURE,userID);
        Database.addNotification(notf);
        current.add_notification(notf);
    }

    public static void notify_order_data(int userID, int orderID) {
        Customer current=Database.getCustomer(userID);
        Order ord=Database.getOrder(orderID);
        String content = "Dear: " + current.getName() +" your order ID is " + ord.getID()
                +"order data is \n"
                + current.getCart().getData();
        notification notf = new notification(content, NotificationType.INFORMATION,userID);
        Database.addNotification(notf);
        current.add_notification(notf);
    }

    public static void notify_success_order(int userID, int orderID) {
        Customer current=Database.getCustomer(userID);
        Order ord=Database.getOrder(orderID);
        String content = "Dear: " +current.getName() +" your order ID is " + ord.getID()
                +"order data is \n"
                + current.getCart().getData()
                + "\n your remaining balance :" + current.getBalance();
        notification notf=new notification(content, NotificationType.INFORMATION,userID);
        Database.addNotification(notf);
        current.add_notification(notf);
    }

    public static void notify_emtpy_order(int userID) {
        Customer current=Database.getCustomer(userID);
        String content = "Dear: " +current.getName() +" the cart is empty";
        notification notf=new notification(content, NotificationType.FAILURE,userID);
        Database.addNotification(notf);
        current.add_notification(notf);
    }
}
