package com.example.demo;

import com.example.demo.service.Catalog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWebApplication {

	public static void main(String[] args) {
		Database database = new Database();
		Catalog catalog = new Catalog();
		SpringApplication.run(HelloWebApplication.class, args);
	}

}
