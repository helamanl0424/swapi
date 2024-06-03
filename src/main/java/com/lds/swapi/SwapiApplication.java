package com.lds.swapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Main Spring Boot application class for the Star Wars API.
 * This application is configured to auto-scan for Spring components,
 * enable JPA repositories, and manage entity scanning within specified packages.
 * It serves as the entry point for the SWAPI service, responsible for managing
 * Star Wars character data and related entities.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.lds.swapi.repository")
@ComponentScan(basePackages = { "com.lds.swapi.*" })
@EntityScan(basePackages = {"com.lds.swapi.model"})
public class SwapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapiApplication.class, args);
	}

}
