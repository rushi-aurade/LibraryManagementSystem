package com.rushikesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.rushikesh.entity.BookEntity;
import com.rushikesh.service.BookService;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

}
