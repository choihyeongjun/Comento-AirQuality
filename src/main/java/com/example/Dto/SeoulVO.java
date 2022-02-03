package com.example.Dto;

import java.util.List;
import java.util.Objects;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SeoulVO {
	@Getter
	@Setter
	@ToString
	public static class GetAirQualityResponse{
		@JsonProperty("RealtimeCityAir")
		private Temp result;
	}
	
	@Getter
	@Setter
	@ToString
	public static class Temp{
		private String list_total_count;
		private String city;
		@JsonProperty("RESULT")
		private Result result;
		@JsonProperty("row")
		private List<Row>rows;
		private double Pm10Avg;
		private String Pm10AvgGrade;
		
		public boolean isSuccess() {
			if(Objects.equals(result.getCode(),"INFO-000")) {
				return true;
			}
			return false;
		}
	}
	@Getter
	@Setter
	@ToString
	public static class Result{
		@JsonProperty("CODE")
		private String code;
		@JsonProperty("MESSAGE")
		private String message;
	}
	@Getter
	@Setter
	@ToString
	public static class Row{
		@JsonProperty("MSRDT")
		private String time;
		@JsonProperty("MSRRGN_NM")
		private String location;
		@JsonProperty("MSRSTE_NM")
		private String gu;
		@JsonProperty("PM10")
		private double pm10;
		@JsonProperty("PM25")
		private double pm25;
		@JsonProperty("O3")
		private double o3;
		@JsonProperty("NO2")
		private double no2;
		@JsonProperty("CO")
		private double co;
		@JsonProperty("SO2")
		private double so2;
		@JsonProperty("IDEX_NM")
		private String totalGrade;
		@JsonProperty("IDEX_MVL")
		private double qualityIndex;
		@JsonProperty("ARPLT_MAIN")
		private String material;
		private String pm10Grade;
		private String pm25Grade;
		private String o3Grade;
		private String no2Grade;
		private String coGrade;
		private String so2Grade;
	}

}
