package com.example.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {

	// TODO: paginate get all products
	// TODO: read about principal, credentials, authorities
	// TODO advance: cache, idempotent, api versioning, write tests unit test
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
		System.out.println("Hello World");
	}


}
