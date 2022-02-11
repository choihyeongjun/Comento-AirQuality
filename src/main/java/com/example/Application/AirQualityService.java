package com.example.Application;

import org.springframework.stereotype.Service;


import com.example.infrastructure.Busan.BusanApiCaller;
import com.example.infrastructure.Seoul.SeoulApiCaller;
import com.example.interfaces.Dto.AirQualityDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AirQualityService {
	 private final SeoulApiCaller seoul;
	 private final BusanApiCaller busan;
	    public AirQualityDto.GetAirQualityInfo getAirQualityInfo(String city,String gu){
	    	
	    	if(city.equals("seoul")) {
	    		
	    		var airQuality=seoul.getAirQuality();
	    		if(gu!=null) {
	    			return airQuality.searchByGu(gu);
	    		}
	    		return airQuality;
	    	}
	    	else if(city.equals("busan")) {
	    		var airQuality=busan.getAirQuality();
	    		if(gu!=null) {
	    			return airQuality.searchByGu(gu);
	    		}
	    		return airQuality;
	    	}

	        throw new RuntimeException(city + " 대기질 정보는 아직 준비중입니다.");
	    }

}
