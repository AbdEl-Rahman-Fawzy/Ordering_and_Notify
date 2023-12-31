package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.*;

public class NotificationService {
    public void notify_failure_order(int userID) {
        Customer current= Database.getCustomer(userID);
        String content = "Dear: " +current.getName() +" you dont have enough balance to make this order \n your cart contains"
                +current.getCart().getData();
        notification notf = new notification(content, NotificationType.FAILURE,userID);
        current.add_notification(notf);
        Database.addNotification(notf);
    }

    public void notify_order_data(int userID, int orderID) {
        Customer current=Database.getCustomer(userID);
        Order ord=Database.getOrder(orderID);
        String content = "Dear: " + current.getName() +", your order ID is " + ord.getID() + "\n"
                + "order data is: " + "\n"
                + current.getCart().getData() + "\n"
                + "your remaining balance : " + current.getBalance() + "\n"
                + "Your order now is on SHIPPING state " + "\n"
                + "You can cancel your order within 2 DAYS from now "
                + "Current date: " + ord.getDate();

        notification notf = new notification(content, NotificationType.INFORMATION,userID);
        notf.setContent(content);
        current.add_notification(notf);
        Database.addNotification(notf);
    }

    public void notify_success_order(int userID, int orderID) {
        Customer current=Database.getCustomer(userID);
        Order ord=Database.getOrder(orderID);
        String content = "Dear: " +current.getName() +" your order ID is " + ord.getID()
                + "Your order has been DELIVERED successfully on " + ord.getDate()
                +"\n order data is "
                + current.getCart().getData()
                + "\n your remaining balance :" + current.getBalance();
        notification notf=new notification(content, NotificationType.SUCCESS,userID);
        Database.addNotification(notf);
        current.add_notification(notf);
    }

    public void notify_empty_order(int userID) {
        Customer current=Database.getCustomer(userID);
        String content = "Dear: " +current.getName() +" the cart is empty";

        notification notf=new notification(content, NotificationType.FAILURE,userID);
        Database.addNotification(notf);
        current.add_notification(notf);
    }
    public void notify_cancel_order(int userID, int orderID) {
        Customer current=Database.getCustomer(userID);
        Order ord=Database.getOrder(orderID);
        String content = "Dear: " + current.getName() + " your order ID is " + ord.getID()
                +" order data is \n"
                + current.getCart().getData() + " is Cancelled"
                + "\n your remaining balance :" + current.getBalance();
        notification notf = new notification(content, NotificationType.CANCEL,userID);
        Database.addNotification(notf);
        current.add_notification(notf);
    }
}
