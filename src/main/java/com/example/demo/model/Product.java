package com.example.demo.model;
public class Product {

    // Properties
    private int id; // New field for id
    private String name;
    private String serialNumber;
    private String vendor;
    private String category;
    private double price;
    private int amount;

    // Constructors
    public Product() {
        // Default constructor
    }

    public Product(int id, String name, String serialNumber, String vendor, String category, double price) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", vendor='" + vendor + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
