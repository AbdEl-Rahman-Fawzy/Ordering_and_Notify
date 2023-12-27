package com.example.demo.model;

import java.util.LinkedList;
import java.util.Queue;

public class Customer {
	private String name;
	private String password;
	private int age;
	private int id;
	private Queue<notification> notifications = new LinkedList<>();
	private Cart cart ;
	private String mail;
	private double balance;

	public Customer() {}

	public Customer(String name, int age, int id, String mail, double balance, String password , int cart_id) {
		this.name = name;
		this.age = age;
		this.id = id;
		this.mail = mail;
		this.balance = balance;
		this.password = password;
		cart = new Cart(cart_id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String get_data() {
		return String.format("ID: %d, Name: %s, Age: %d, Mail: %s", id, name, age, mail);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Cart getCart() {
		return cart;
	}


	public Queue<notification> getNotifications() {
		return notifications;
	}

	public void setAge(int i) {
		this.age= i;
	}

	public double getBalance() {
		return balance;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
