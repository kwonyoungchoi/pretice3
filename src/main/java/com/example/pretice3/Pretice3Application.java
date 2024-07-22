package com.example.pretice3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Pretice3Application {

	public static void main(String[] args) {
		SpringApplication.run(Pretice3Application.class, args);
	}

}
