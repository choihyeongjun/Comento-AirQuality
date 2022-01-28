package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@AutoConfigurationPackage
@ComponentScan("com.example")
public class CityAirQualityApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(CityAirQualityApplication.class, args);
	}

}
