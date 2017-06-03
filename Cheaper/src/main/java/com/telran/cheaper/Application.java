package com.telran.cheaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationProperties
@ComponentScan("com.telran")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
