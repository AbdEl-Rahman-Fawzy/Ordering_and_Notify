package com.example.demo.model;

public class notification {
    private int ID;
    private int notify_user_id ;
    // Property
    private String content;
    private NotificationType type;

    public notification(String content, NotificationType type) {
        this.content = content;
        this.type = type;
    }

    // Constructor
    public notification() {
        // Default constructor
    }
    public notification(int x) {
        notify_user_id  = x;
        // Default constructor
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getNotify_user_id() {
        return notify_user_id;
    }

    public void setID(int generateNotificationId) {
        ID=generateNotificationId;
    }
    public int getID() {
        return ID;
    }
}