package com.lojaunit.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages="com.lojaunit")
@EntityScan("com.lojaunit.model")
@EnableJpaRepositories("com.lojaunit.repository")
public class LojaUnitApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaUnitApplication.class, args);
	}
}
