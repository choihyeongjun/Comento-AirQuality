package com.example.infrastructure.Busan;

import java.io.IOException;
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
public class BusanApiCaller {
	private final BusanSetting busanAirQualityApi;

    public BusanApiCaller(@Value("${api.busan.base-url}") String baseUrl) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.busanAirQualityApi = retrofit.create(BusanSetting.class);
        System.out.println("부산콜러");
    }

    public AirQualityDto.GetAirQualityInfo getAirQuality() {
        try {
            var call = busanAirQualityApi.getAirQuality();
            var response = call.execute().body();
            System.out.println(response);

            if (response == null || response.getResponse() == null) {
                throw new RuntimeException("[busan] getAirQuality 응답값이 존재하지 않습니다.");
            }

            if (response.getResponse().isSuccess()) {
            	log.info(response.toString());
                return parsing(response);
            }

            throw new RuntimeException("[busan] getAirQuality 응답이 올바르지 않습니다. header=" + response.getResponse().getHeader());

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("[busan] getAirQuality API error 발생! errorMessage=" + e.getMessage());
        }
    }
    private AirQualityDto.GetAirQualityInfo parsing(BusanVO.GetAirQualityResponse response){
    	var rows=response.getResponse().getItems();
    	var Pm10Avg=averagePm10(rows);
    	var Pm10AvgGrade=GradeCheck.getPm10Grade(Pm10Avg);
    	var guList=rowsparsing(rows);
    	return AirQualityDto.GetAirQualityInfo.builder()
    			.city("busan")
    			.pm10Avg(Pm10Avg)
    			.pm10Grade(Pm10AvgGrade)
    			.guList(guList)
    			.build();
    	
    }
    private List<AirQualityDto.GetGuAirQualityInfo>rowsparsing(List<BusanVO.Item>rows){
    	return rows.stream().map(
    			row->new AirQualityDto.GetGuAirQualityInfo(row.getSite(),
    					Double.parseDouble(row.getPm10()),Double.parseDouble(row.getPm25()),
    					Double.parseDouble(row.getO3()),
    					Double.parseDouble(row.getNo2()),
    					Double.parseDouble(row.getCo()),
    					Double.parseDouble(row.getSo2()))
    			).collect(Collectors.toList());
    }
    private Double averagePm10(List<BusanVO.Item>rows) {
    	double result=0;
    	for(int i=0;i<rows.size();i++) {
    		result+=Double.parseDouble(rows.get(i).getPm10());
    	}
    	return result;
    }
}
