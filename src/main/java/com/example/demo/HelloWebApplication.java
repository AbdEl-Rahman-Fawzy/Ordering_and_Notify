package com.example.demo;

import com.example.demo.service.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloWebApplication {

	public static void main(String[] args) {
		Database database = new Database();
		NotificationService n = new NotificationService();
		Catalog catalog = new Catalog();
		SpringApplication.run(HelloWebApplication.class, args);
	}
	@GetMapping
	public String hello(){
		return "hello world";
	}

}
