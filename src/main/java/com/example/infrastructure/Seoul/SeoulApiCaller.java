package com.example.infrastructure.Seoul;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import com.example.Util.GradeCheck;
import com.example.interfaces.Dto.AirQualityDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;


import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;



@Slf4j
@Component
public class SeoulApiCaller{
	
	private final SeoulSetting seoul;
	
	
	public SeoulApiCaller(@Value("${api.seoul.base-url}")String baseUrl) {
		ObjectMapper objectMapper=new ObjectMapper();
	
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		System.out.println(baseUrl);
		Retrofit retrofit=new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(JacksonConverterFactory.create(objectMapper))
				.build();
		this.seoul=retrofit.create(SeoulSetting.class);
		//log.info(seoul.toString());
		System.out.println("서울caller");
		
		
	}

	public AirQualityDto.GetAirQualityInfo getAirQuality(){
		try {
			var call=seoul.getAirQuality();
			var response=call.execute().body();
			System.out.println("seoulgetAir");
			if(response==null||response.getResult()==null) {
				throw new RuntimeException("[seoul]getAirQuality 응답값이 존재하지않습니다");
			}
			if(response.getResult().isSuccess()) {
				log.info(response.toString());
				return parsing(response);
			}
			throw new RuntimeException("[seoul]getAirQuality 응답이 올바르지않습니다."+response.getResult());
		}catch(IOException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException("[seoul]getAirQuality Error 발생"+e.getMessage());
		} 
		
	}
	private AirQualityDto.GetAirQualityInfo parsing(SeoulVO.GetAirQualityResponse response){
		var rows=response.getResult().getRows();
		var Pm10Avg=averagePm10(rows);
		var Pm10AvgGrade=GradeCheck.getPm10Grade(Pm10Avg);
		var guList=rowsparsing(rows);
		return AirQualityDto.GetAirQualityInfo.builder().city("seoul").pm10Avg(Pm10Avg)
				.pm10Grade(Pm10AvgGrade)
				.guList(guList)
				.build();
	}
	private List<AirQualityDto.GetGuAirQualityInfo>rowsparsing(List<SeoulVO.Row>rows){
		return rows.stream().map(row->new AirQualityDto.GetGuAirQualityInfo
				(row.getGu(),
				row.getPm10(),
				row.getPm25(),
				row.getO3(),row.getNo2(),row.getCo(),row.getSo2())
				).collect(Collectors.toList());
				
	}
	private Double averagePm10(List<SeoulVO.Row>rows) {
		double result=0;
		for(int i=0;i<rows.size();i++) {
			result+=rows.get(i).getPm10();
		}
		return result/rows.size();
	}
}
