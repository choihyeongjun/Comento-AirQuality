package com.example.CityAirQuality;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;


import com.example.CityAirQualityApplication;
import com.example.infrastructure.Seoul.SeoulApiCaller;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.json.simple.parser.ParseException;


@SpringBootTest(classes=CityAirQualityApplication.class)
@Slf4j
class CityAirQualityApplicationTests {
	 
	
	@Autowired
	SeoulApiCaller seoul;
	
	
	@Test
	public void contextLoads() {
		var response=seoul.getAirQuality();
		
		assertNotNull(response);
	}

}
