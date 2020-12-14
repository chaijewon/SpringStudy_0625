package com.sist.webapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
@RestController
// 코틀린에서 데이터 받아서 처리 
@RequestMapping("food/")
public class MainRestController {
   @Autowired
   private FoodDAO dao;
   @RequestMapping(value="kotlin_list.do",produces="text/plain;charset=UTF-8")
   public String food_kotlin_list(String no)
   {
	   String result="";
	   if(no==null) // 첫화면 => default 
		   no="1";
	   int in=Integer.parseInt(no);
	   int start=0;
	   int end=0;
	   if(in==1)
	   {
		   start=1;
		   end=12;
	   }
	   else if(in==2)
	   {
		   start=13;
		   end=18;
	   }
	   else if(in==3)
	   {
		   start=19;
		   end=30;
	   }
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end",end);
	   List<FoodCategoryVO> list=dao.foodCategoryData(map);
	   try
	   {
		   // Kotlin => 전송 (JSON) [{no:값...},{},{}...]
		   JSONArray arr=new JSONArray();
		   for(FoodCategoryVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("no", vo.getNo());
			   obj.put("title", vo.getTitle());
			   obj.put("subject", vo.getSubject());
			   obj.put("poster", vo.getPoster());
			   arr.add(obj);
		   }
		   result=arr.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
}












