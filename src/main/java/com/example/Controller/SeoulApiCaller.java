package com.example.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.Dto.SeoulVO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;



@Slf4j
@Component
public class SeoulApiCaller {
	
	private final SeoulSetting seoul;
	
	
	public SeoulApiCaller(@Value("${api.seoul.base-url}")String baseUrl) {
		ObjectMapper objectMapper=new ObjectMapper();
	
	//	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		System.out.println(baseUrl);
		Retrofit retrofit=new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(JacksonConverterFactory.create(objectMapper))
				.build();
		this.seoul=retrofit.create(SeoulSetting.class);
		//log.info(seoul.toString());
		
		
	}

	public SeoulVO.GetAirQualityResponse getAirQuality(){
		try {
			var call=seoul.getAirQuality();
			var response=call.execute().body();
			
			if(response==null||response.getResult()==null) {
				throw new RuntimeException("getAirQuality 응답값이 존재하지않습니다");
			}
			if(response.getResult().isSuccess()) {
				log.info(response.toString());
				return response;
			}
			throw new RuntimeException("getAirQuality 응답이 올바르지않습니다."+response.getResult());
		}catch(IOException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException("getAirQuality Error 발생"+e.getMessage());
		} 
		
	}
}
