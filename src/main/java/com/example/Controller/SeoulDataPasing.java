package com.example.Controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class SeoulDataPasing {
	@Autowired
	SeoulApiCaller seoul;
	public String GradeCheck(double value,int index) {
		//index 1:Pm10 2:Pm25 3:O3 4:No2 5:Co 6:SO2
		String result="";
		if(index==1) {
			if(value<=50)
				result="좋음";
			else if(value<=100)
				result="보통";
			else if(value<=250)
				result="나쁨";
			else
				result="매우나쁨";
		}
		else if(index==2) {
			if(value<=15)
				result="좋음";
			else if(value<=35)
				result="보통";
			else if(value<=75)
				result="나쁨";
			else
				result="매우나쁨";
		}
		else if(index==3) {
			if(value<=0.03)
				result="좋음";
			else if(value<=0.09)
				result="보통";
			else if(value<=0.15)
				result="나쁨";
			else
				result="매우나쁨";
		}
		else if(index==4) {
			if(value<=0.03)
				result="좋음";
			else if(value<=0.06)
				result="보통";
			else if(value<=0.2)
				result="나쁨";
			else
				result="매우나쁨";
		}
		else if(index==5) {
			if(value<=2)
				result="좋음";
			else if(value<=9)
				result="보통";
			else if(value<=15)
				result="나쁨";
			else
				result="매우나쁨";
		}
		else if(index==6) {
			if(value<=0.02)
				result="좋음";
			else if(value<=0.05)
				result="보통";
			else if(value<=0.15)
				result="나쁨";
			else
				result="매우나쁨";
		}
		return result;
		
	}
	public JSONObject ParsingData() {
		var response=seoul.getAirQuality();
		double Pm10Avg = 0;
		double Pm25=0;
		double O3=0;
		double No2=0;
		double Co=0;
		double So2=0;
		String Pm10AvgGrade="";
		String Pm25Grade="";
		String O3Grade="";
		String No2Grade="";
		String CoGrade="";
		String So2Grade="";
		
		for(int i=0;i<25;i++) {
			Pm10Avg+=response.getResult().getRows().get(i).getPm10();	
		}
		for(int i=0;i<25;i++) {
			Pm25=response.getResult().getRows().get(i).getPm25();
			response.getResult().getRows().get(i).setPm25Grade(GradeCheck(Pm25,2));
			O3=response.getResult().getRows().get(i).getO3();
			response.getResult().getRows().get(i).setO3Grade(GradeCheck(O3,3));
			No2=response.getResult().getRows().get(i).getNo2();
			response.getResult().getRows().get(i).setNo2Grade(GradeCheck(No2,4));
			Co=response.getResult().getRows().get(i).getCo();
			response.getResult().getRows().get(i).setCoGrade(GradeCheck(Co,5));
			So2=response.getResult().getRows().get(i).getO3();
			response.getResult().getRows().get(i).setSo2Grade(GradeCheck(So2,6));
		}
		Pm10Avg=Pm10Avg/25;
		Pm10AvgGrade=GradeCheck(Pm10Avg,1);
		response.getResult().setPm10Avg(Pm10Avg);
		response.getResult().setPm10AvgGrade(Pm10AvgGrade);

		System.out.println(response);
		JSONObject object=(JSONObject) new JSONObject(response).get("result");
	
		JSONArray arr=(JSONArray) object.get("rows");
		JSONObject aa = null;
		
		for(int i=0;i<25;i++) {
			aa=(JSONObject) arr.get(i);
			aa.remove("qualityIndex");
			aa.remove("totalGrade");
			aa.remove("material");
			aa.remove("time");
		}
		
		JSONObject combind=new JSONObject();
		
		//System.out.println("re"+arr);
		object.remove("result");
		object.remove("list_total_count");
		object.remove("success");
		object.remove("totalGrade");
		combind.put("avg",object);
		//combind.put("rows",aa);
		//System.out.println("com"+combind);
		return object;
	}

}
