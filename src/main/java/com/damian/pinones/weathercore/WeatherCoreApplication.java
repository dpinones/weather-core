package com.damian.pinones.weathercore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.damian.pinones.weathercore.*")
@EntityScan("com.damian.pinones.weathercore.*")
@EnableJpaRepositories("com.damian.pinones.weathercore.*")
@EnableAutoConfiguration
public class WeatherCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherCoreApplication.class, args);
	}

}
