package com.example.Dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SeoulVO {
	@Getter
	@Setter
	@ToString
	public static class GetAirQualityResponse{
		private Result result;
	}
	
	@Getter
	@Setter
	@ToString
	public static class Result{
		private Header header;
		@JsonProperty("item")
		private List<Item>items;
		private int totalCnt;
		private String totlaGrade;
		private int qualityIndex;
		private String material;
		public boolean isSuccess() {
			if(Objects.equals(header.getResultCode(),"INFO-000"))
				return true;
			return false;
		}
	}
	@Getter
	@Setter
	@ToString
	public static class Header{
		private String resultCode;
		private String resultMsg;
	}
	@Getter
	@Setter
	@ToString
	public static class Item{
		private int time;
		private String region;
		private String location;
		private int pm10;
		private int pm25;
		private int o3;
		private int no2;
		private int co;
		private int so2;
	}
}
