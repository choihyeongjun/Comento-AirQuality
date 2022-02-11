package com.example.interfaces.Dto;


import java.util.Collections;
import java.util.List;

import com.example.Util.GradeCheck;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



public class AirQualityDto {
	
	@Getter
	@Setter
	@Builder
	public static class GetAirQualityInfo{
		private String city;
		private double pm10Avg;
		private String pm10Grade;
		private List<GetGuAirQualityInfo>guList;
		  public GetAirQualityInfo searchByGu(String gu) {
	            if (gu == null) {
	                return this;
	            }
	            var searchedGuInfo = searchGuAirQualityInfo(gu);
	            guList = Collections.singletonList(searchedGuInfo);
	            return this;
	        }

	        private GetGuAirQualityInfo searchGuAirQualityInfo(String gu) {
	            return guList.stream()
	                    .filter(guAirQualityInfo -> guAirQualityInfo.getGu().equals(gu))
	                    .findFirst()
	                    .orElseThrow(() -> new IllegalArgumentException(gu + "에 해당하는 자치구가 존재하지 않습니다."));
	        }
	}
	
	@Getter
	public static class GetGuAirQualityInfo{
		private final String gu;
        private final double pm10;
        private final double pm25;
        private final double o3;
        private final double no2;
        private final double co;
        private final double so2;
        private final String pm10Grade;
        private final String pm25Grade;
        private final String o3Grade;
        private final String no2Grade;
        private final String coGrade;
        private final String so2Grade;
        
        public GetGuAirQualityInfo(String gu,double pm10,double pm25,double o3,double no2,double co,double so2) {
        	this.gu=gu;
        	this.pm10=pm10;
        	this.pm25=pm25;
        	this.o3=o3;
        	this.no2=no2;
        	this.co=co;
        	this.so2=so2;
        	this.pm10Grade=GradeCheck.getPm10Grade(pm10);
        	this.pm25Grade=GradeCheck.getPm25Grade(pm25);
        	this.o3Grade=GradeCheck.getO3Grade(o3);
        	this.no2Grade=GradeCheck.getNo2Grade(no2);
        	this.coGrade=GradeCheck.getCoGrade(co);
        	this.so2Grade=GradeCheck.getSo2Grade(so2);
        }
	}

}
