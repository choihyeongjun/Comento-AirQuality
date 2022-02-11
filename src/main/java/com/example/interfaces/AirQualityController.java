package com.example.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.Application.AirQualityService;

import com.example.interfaces.Dto.AirQualityDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AirQualityController {
	 private final AirQualityService airQualityService;

	    @GetMapping("/v1/api/air-quality/{city}")
	    public AirQualityDto.GetAirQualityInfo getAirQualityInfo(@PathVariable("city") String city,
	                                                             @RequestParam(required = false) String gu) {
	    	System.out.println("controller");
	        return airQualityService.getAirQualityInfo(city, gu);
	    }

}
