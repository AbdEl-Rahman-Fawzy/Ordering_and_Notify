package com.example.demo;

import com.example.demo.service.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWebApplication {

	public static void main(String[] args) {
		Database database = new Database();
		NotificationService n = new NotificationService();
		Catalog catalog = new Catalog();
		SpringApplication.run(HelloWebApplication.class, args);
	}

}
