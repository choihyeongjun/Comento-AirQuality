package com.example.Controller;

import com.example.Dto.SeoulVO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SeoulSetting {
	String key="536c58687267757531334b46554b41";
	
	@GET(key+"/json/RealtimeCityAir/1/25/")
	
	Call<SeoulVO.GetAirQualityResponse> getAirQuality();
	
}
