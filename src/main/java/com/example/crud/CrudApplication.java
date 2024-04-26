package com.example.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CrudApplication {

	// TODO: read about principal, credentials, authorities
	// TODO advance: cache, idempotent, api versioning, write tests unit test
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
		System.out.println("Hello World");
	}


}
