package com.example.CityAirQuality;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.CityAirQualityApplication;
import com.example.Controller.SeoulApiController;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes=CityAirQualityApplication.class)

class CityAirQualityApplicationTests {
	
	
	@Autowired
	SeoulApiController seoul;
	
	
	@Test
	public void contextLoads() {
		var response=seoul.getAirQuality();
		
		assertNotNull(response);
	}

}
