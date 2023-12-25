package com.example.demo.model;

import java.util.LinkedList;
import java.util.Queue;

public class Customer {
	private String name;
	private int age;
	private int id;
	private Queue<notification> notifications = new LinkedList<>();
	private Cart cart = new Cart();
	private String mail;
	private int balance;

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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
