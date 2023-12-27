package com.example.demo.service;

import com.example.demo.Database;
import com.example.demo.model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NotificationService {
//    public static notification getFailureOrder(int userID) {
//        Customer current=Database.getCustomer(userID);
//        String content = "Dear: " +current.getName() +" you dont have enough balance to make this order \n your cart contains"
//                +current.getCart().getData();
//        notification notf=new notification(content, NotificationType.FAILURE,userID);
//        Database.addNotification(notf);
//        return notf;
//    }
    public static String getFailureOrder(int userID) {
        Customer current=Database.getCustomer(userID);
        String content = "Dear: " +current.getName() +" you dont have enough balance to make this order \n your cart contains"
                +current.getCart().getData();
        notification notf=new notification(content, NotificationType.FAILURE,userID);
        Database.addNotification(notf);
        return "notf1";
    }

    public static String getOderData(int userID,int orderID) {
        Customer current=Database.getCustomer(userID);
        Order ord=Database.getOrder(orderID);
        String content = "Dear: " + current.getName() +" your order ID is " + ord.getID()
                +"order data is \n"
                + current.getCart().getData();
        notification notf = new notification(content, NotificationType.INFORMATION,userID);
        Database.addNotification(notf);
        return "notification2";
    }
//    public static notification getOderData(int userID,int orderID) {
//        Customer current=Database.getCustomer(userID);
//        Order ord=Database.getOrder(orderID);
//        String content = "Dear: " + current.getName() +" your order ID is " + ord.getID()
//                +"order data is \n"
//                + current.getCart().getData();
//        notification notf = new notification(content, NotificationType.INFORMATION,userID);
//        Database.addNotification(notf);
//        return notf;
//    }

//    public static notification getSuccessSimpleOrder(int userID,int orderID) {
//        Customer current=Database.getCustomer(userID);
//        Order ord=Database.getOrder(orderID);
//        String content = "Dear: " +current.getName() +" your order ID is " + ord.getID()
//                +"order data is \n"
//                + current.getCart().getData()
//                + "\n your remaining balance :" + current.getBalance();
//        notification notf=new notification(content, NotificationType.INFORMATION,userID);
//        Database.addNotification(notf);
//        return notf;
//    }
    public static String getSuccessSimpleOrder(int userID,int orderID) {
        Customer current=Database.getCustomer(userID);
        Order ord=Database.getOrder(orderID);
        String content = "Dear: " +current.getName() +" your order ID is " + ord.getID()
                +"order data is \n"
                + current.getCart().getData()
                + "\n your remaining balance :" + current.getBalance();
        notification notf=new notification(content, NotificationType.INFORMATION,userID);
        Database.addNotification(notf);
        return "notf";
    }

//    public static notification emptyCartOrder(int userID) {
//        Customer current=Database.getCustomer(userID);
//        String content = "Dear: " +current.getName() +" the cart is empty";
//        notification notf=new notification(content, NotificationType.FAILURE,userID);
//        Database.addNotification(notf);
//        return notf;
//    }
    public static String emptyCartOrder(int userID) {
        Customer current=Database.getCustomer(userID);
        String content = "Dear: " +current.getName() +" the cart is empty";
        notification notf=new notification(content, NotificationType.FAILURE,userID);
        Database.addNotification(notf);
        return "notf3";
    }
}
