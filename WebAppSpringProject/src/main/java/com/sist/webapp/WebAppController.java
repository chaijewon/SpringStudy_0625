package com.sist.webapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.sist.dao.*;
@RestController
@CrossOrigin("http://localhost:3000")
public class WebAppController {
   // DAO객체 얻기
   @Autowired
   private MovieDAO dao;
   @RequestMapping(value="movie/list.do",produces="text/plain;charset=UTF-8")
   // 1. React(JavaScript),Kotlin
   public String movie_list(String page,String cateno)
   {
	   String result="";
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=12;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   map.put("start", start);
	   map.put("end", end);
	   map.put("cateno", cateno);
	   List<MovieVO> list=dao.movieListData(map);
	   // list => 데이터를 JSON => 전송 준비 (React,Kotlin)
	   /*
	    *    React => axios("URL") => JSON 파싱 
	    *    Kotlin => HttpUrlConnection("URL") => 파싱 (gson)
	    *    {},{},{},{}
	    *    ==
	    *    한개에 대한 정보 (오라클 => row)
	    *    ==> 12개 [{},{},{}...]
	    */
	   try
	   {
		   // no,cateno,title,poster,score,regdate
		   JSONArray arr=new JSONArray();
		   for(MovieVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("no", vo.getNo());// {no:1,title:'',poster:''....}
			   obj.put("poster", "http:"+vo.getPoster());
			   obj.put("title", vo.getTitle());
			   obj.put("score", vo.getScore());
			   obj.put("regdate", vo.getRegdate());
			   arr.add(obj);
		   }
		   result=arr.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
   @RequestMapping("movie/total.do")
   public String movie_total(int cateno)
   {
	   String result="";
	   int total=dao.movieTotalPage(cateno);
	   result=String.valueOf(total);
	   return result;
   }
   /*
    *  private int no,cateno,hit;
    private String title,poster,regdate,genre,grade,actor,score,director,story,key;
    */
   @RequestMapping(value="movie/detail.do",produces="text/plain;charset=UTF-8")
   public String movie_detail(int no)
   {
	   String result="";
	   try
	   {
		   MovieVO vo=dao.movieDetailData(no);
		   JSONObject obj=new JSONObject(); // {}
		   obj.put("poster", "http:"+vo.getPoster());
		   obj.put("title", vo.getTitle());
		   obj.put("director", vo.getDirector());
		   obj.put("actor", vo.getActor());
		   obj.put("genre", vo.getGenre());
		   obj.put("score", vo.getScore());
		   obj.put("story", vo.getStory());
		   
		   result=obj.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
}











