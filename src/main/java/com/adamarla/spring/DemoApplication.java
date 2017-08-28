package com.adamarla.spring;

import com.adamarla.spring.util.SourceIndexer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public DemoApplication() {
		System.out.println("DemoApplication -->");
		new SourceIndexer();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
