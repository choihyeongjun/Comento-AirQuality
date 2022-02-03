package com.example.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dto.SeoulVO.GetAirQualityResponse;
import com.example.Dto.SeoulVO.Row;


//@RestController
@Controller
public class SeoulController {
	
	@Autowired
	SeoulApiCaller seoul;
	@Autowired
	SeoulDataPasing paring;
	@GetMapping("/v1/api/air-quality/{city}")
	public String SeoulAll(@PathVariable("city")String city,Model model) {
		JSONObject object=paring.ParsingData();
		object.put("city", city);
		System.out.println(city);
		System.out.println("city"+object);
		model.addAttribute("response", object);
		return "/responseall";
		
	}
	
	@GetMapping("/v1/api/air-quality/selectgu/{city}/{gu}")//원하는 구 조회
	public String SeoulGu(@PathVariable("city")String city,@PathVariable("gu")String gu,Model model) {
		JSONObject object=paring.ParsingData();
		object.put("city", city);
		//System.out.println(gu+"??");
		JSONArray arr=(JSONArray)object.get("rows");
		//Object aa=object.get("rows");
		//System.out.println("arr?????????"+arr);
		//System.out.println(aa);
		JSONObject object1=null;
		JSONObject object2=null;
		
		
		for(int i=0;i<25;i++) {
			object1=(JSONObject)arr.get(i);
			if(object1.get("gu").equals(gu)) {
				object2=object1;
			}
			
		}
		model.addAttribute("response",object2);
		return "/responseall";
		
	}

	@GetMapping("/v1/api/air-quality/selectlocation/{city}/{location}")//원하는 권 조회
	public String SeoulLocation(@PathVariable("city")String city,@PathVariable("location")String location,Model model){
		
		JSONObject object=paring.ParsingData();
		object.put("city", city);
		//System.out.println(gu+"??");
		JSONArray arr=(JSONArray)object.get("rows");
		//Object aa=object.get("rows");
		//System.out.println("arr?????????"+arr);
		//System.out.println(aa);
		JSONObject object1=null;
		JSONArray object2=new JSONArray();
		
		
		for(int i=0;i<25;i++) {
			object1=(JSONObject)arr.get(i);
			if(object1.get("location").equals(location)) {
				object2.put(object1);
			}
			
		}
		model.addAttribute("response",object2);
		return "/responseall";
	}

}
