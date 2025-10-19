package com.example.evsalesmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing 

public class EvsalesmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvsalesmanagementApplication.class, args);
	}

}
