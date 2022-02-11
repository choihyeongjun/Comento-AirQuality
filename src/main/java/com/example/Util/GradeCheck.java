package com.example.Util;

public class GradeCheck {
	public static String getPm25Grade(double value) {
		if(value<=15)
			return "좋음";
		else if(value<=35)
			return"보통";
		else if(value<=75)
			return "나쁨";
		else
			return "매우나쁨";
	}
	public static String getPm10Grade(double value) {
		if(value<=50)
			return "좋음";
		else if(value<=100)
			return "보통";
		else if(value<=250)
			return "나쁨";
		else
			return "매우나쁨";
	}
	public static String getO3Grade(double value) {
		if(value<=0.03)
			return "좋음";
		else if(value<=0.09)
			return "보통";
		else if(value<=0.15)
			return "나쁨";
		else
			return "매우나쁨";
	}
	public static String getNo2Grade(double value) {
		if(value<=0.03)
			return "좋음";
		else if(value<=0.06)
			return "보통";
		else if(value<=0.2)
			return "나쁨";
		else
			return "매우나쁨";
	}
	public static String getCoGrade(double value) {
		if(value<=2)
			return "좋음";
		else if(value<=9)
			return "보통";
		else if(value<=15)
			return "나쁨";
		else
			return "매우나쁨";
		
	}
	public static String getSo2Grade(double value) {
		if(value<=0.02)
			return "좋음";
		else if(value<=0.05)
			return "보통";
		else if(value<=0.15)
			return "나쁨";
		else
			return "매우나쁨";
	}
}
