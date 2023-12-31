package com.example.demo;

import com.example.demo.model.Catalog;
import com.example.demo.service.NotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class HelloWebApplication {

	public static void main(String[] args) {
		Database database = new Database();
		Catalog catalog = new Catalog();
		NotificationService notificationService = new NotificationService();
		SpringApplication.run(HelloWebApplication.class, args);

		// Create a scheduled executor service
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

		// Schedule a task to run every 5 seconds
		executorService.scheduleAtFixedRate(() -> {
			// Check if the notification queue is not empty
			if (!Database.getNotificationQueue().isEmpty()) {
				// Pop the first notification from the queue
				Database.getNotificationQueue().poll();
			}
		}, 0, 40, TimeUnit.SECONDS);
	}
}
