package com.example.evsalesmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing 
@EnableCaching
public class EvsalesmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvsalesmanagementApplication.class, args);
		System.out.println("EV Sales Management Application is running...");
	}

}
