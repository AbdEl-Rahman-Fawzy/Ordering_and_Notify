package com.example.demo.model;

public class notification
{
    // Property
    private int ID;
    private int notify_user_id ;
    private String content;
    private NotificationType type;
    public notification(String content, NotificationType type,int customerID) {
        this.content = content;
        this.type = type;
        notify_user_id=customerID;
    }
    public notification() {}

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