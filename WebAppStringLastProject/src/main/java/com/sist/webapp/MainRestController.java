package com.sist.webapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.annotation.Resource;

import com.sist.dao.*;
import com.sist.news.*;
import com.sist.recommand.NaverBlogManager;
import com.sist.recommand.RecommandManager;
@RestController
@CrossOrigin("http://localhost:3000")
// 코틀린에서 데이터 받아서 처리 
@RequestMapping("food/")
public class MainRestController {
   @Autowired // 스프링 자동으로 해당 객체주소를 찾아서 주입
   private FoodDAO dao;
   
   @Resource(name="mgr") // 특정 객체 지정 
   private NewsManager mgr;
   
   @Autowired
   private NaverBlogManager nbm;
   
   @Autowired
   private RecommandManager rm;
   
   @Autowired
   private RecipeDAO rDao;
   
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
   @RequestMapping(value="kotlin_food.do",produces="text/plain;charset=UTF-8")
   public String food_kotlin_food(int cateno)
   {
	   String result="";
	   try
	   {
		   List<FoodDetailVO> list=dao.foodCategoryDetailData(cateno);
		   // list에 존재하는 데이터를 JSON으로 변경 
		   //[]
		   JSONArray arr=new JSONArray();
		   for(FoodDetailVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("no", vo.getNo());
			   obj.put("cateno", cateno);
			   obj.put("title", vo.getTitle());
			   obj.put("score", vo.getScore());
			   obj.put("poster", vo.getPoster());
			   obj.put("addr", vo.getAddr());
			   obj.put("tel", vo.getTel());
			   arr.add(obj);
		   }
		   result=arr.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
   /*
    *    WEB ========> http ======= 서버(Spring)
    *       <========  http =======
    *       
    *    모바일  =======> http ====== 서버(Spring) ==> 클라이언트 프로그램 제작 
    *         <======= WAP == http ======
    *    CHAT ==> WS
    */
   @RequestMapping(value="kotlin_detail.do",produces="text/plain;charset=UTF-8")
   public String food_kotlin_detail(int no)
   {
	   String result="";
	   try
	   {
		   FoodDetailVO vo=dao.foodDetailData(no);
		   // {} => 한개의 정보 ==> JSONObject
		   // [] => 여러개의 정보 ==> JSONArray
		   String ss=vo.getPoster();
		   StringTokenizer st=new StringTokenizer(ss,"^");
		   String[] sss=new String[5];
		   int i=0;
		   while(st.hasMoreTokens())
		   {
			   sss[i]=st.nextToken();
			   System.out.println(sss[i]);
			   i++;
		   }
		   JSONObject obj=new JSONObject();
		   obj.put("no", vo.getNo());//{no:1,cateno:2....} => JavaScript:객체단위
		   // ==> 자바의 VO를 제작한다 
		   obj.put("cateno", vo.getCateno());
		   obj.put("title", vo.getTitle());
		   obj.put("score", vo.getScore());
		   obj.put("poster1", sss[0]);
		   obj.put("poster2", sss[1]);
		   obj.put("poster3", sss[2]);
		   obj.put("poster4", sss[3]);
		   obj.put("poster5", sss[4]);
		   obj.put("addr", vo.getAddr());
		   obj.put("tel", vo.getTel());
		   obj.put("type", vo.getType());
		   obj.put("price", vo.getPrice());
		   obj.put("menu", vo.getMenu());
		   obj.put("good", vo.getGood());
		   obj.put("soso", vo.getSoso());
		   obj.put("bad", vo.getBad());
		   
		   result=obj.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
   
   @RequestMapping(value="kotlin_news.do",produces="text/plain;charset=UTF-8")
   public String food_kotlin_news(String fd)
   {
	   String result="";
	   try
	   {
		   if(fd==null || fd.equals(""))
		   {
			   fd="맛집";
		   }
		   
		   List<Item> list=mgr.newsListData(fd);
		   // JSON 변환 
		   // item ==> 50 => []
		   JSONArray arr=new JSONArray();
		   for(Item i:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("title", i.getTitle());
			   obj.put("description", i.getDescription());
			   obj.put("author", i.getAuthor());
			   obj.put("link", i.getLink());
			   
			   arr.add(obj);
		   }
		   
		   result=arr.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
   @RequestMapping(value="kotlin_recommand.do",produces="text/plain;charset=UTF-8")
   public String kotlin_recommand(String fd)
   {
	   System.out.println("연결");
	   String result="";
	   // XML코드 제작
	   nbm.naverFindData(fd);
	   List<FoodDetailVO> list=rm.foodRecommandData();
	   try
	   {
		   // JSON
		   //[{},{}...]
		   JSONArray arr=new JSONArray();
		   for(FoodDetailVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("no", vo.getNo());
			   obj.put("title", vo.getTitle());
			   obj.put("poster", vo.getPoster());
			   arr.add(obj);
		   }
		   result=arr.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
   // Redux
   @RequestMapping(value="kotlin_recipe.do",produces="text/plain;charset=UTF-8")
   public String food_kotlin_recipe(int page)
   {
	   String result="";
	   try
	   {
		   // 10 => {},{},{} ==> []
		   JSONArray arr=new JSONArray();
		   // 몽고디비로부터 데이터 받기 
		   List<RecipeVO> list=rDao.recipeListData(page);
		   for(RecipeVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("no", vo.getNo());
			   obj.put("title", vo.getTitle());
			   obj.put("poster", vo.getPoster());
			   obj.put("chef", vo.getChef());
			   obj.put("hit", vo.getHit());
			   
			   arr.add(obj);
		   }
		   result=arr.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
   // 쉐프  => recipe 상세 , 쉐프 상세보기 
   /*
    *   @Controller ==> 화면 이동 (forward(request를 전송) , redirect) :WEB
    *   @RestController ==> 화면에 필요한 데이터를 보내준다 (JSON,script,XML,일반문자열)
    *    ==============
    *      react+spring , ajax+spring , kotlin+spring => 자바가 아닌 다른 프로그램을 이용시 
    *      유지 보수 : Spring이 낮은 버전 (@RestController(X), => @ResponseBody)
    *      
    *   => Spring 기반 (Java:openjdk) => JSP (중간에 실시간,변경사항이 많은 곳 => react)
    *   => SI (Web,Mobile)
    *         ============ 부서변경(AI,RPA)
    */
   @RequestMapping(value="kotlin_chef.do",produces="text/plain;charset=UTF-8")
   public String recipe_kotlin_chef(int page)
   {
	   // Model(X)=> web에 값을 전송 => return => 브라우저에 JSON출력 => kotlin,react,nodejs,ajax
	   String result=""; //JSON
	   try
	   {
		   List<ChefVO> list=rDao.chefListData(page);
		   // JSON => Kotlin로 전송 
		   //[] => JSONArray
		   //{} => JSONObject
		   JSONArray arr=new JSONArray();
		   for(ChefVO vo:list)
		   {
			   JSONObject obj=new JSONObject();
			   obj.put("chef", vo.getChef());
			   obj.put("poster", vo.getPoster());
			   obj.put("mc1", vo.getMem_cont1());
			   obj.put("mc2", vo.getMem_cont2());
			   obj.put("mc3", vo.getMem_cont3());
			   obj.put("mc7", vo.getMem_cont7());
			   
			   arr.add(obj);
		   }
		   result=arr.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
   /*
    *      vo.setTitle(obj.getString("title"));
		   vo.setContent(obj.getString("content"));
		   vo.setPoster(obj.getString("poster"));
		   vo.setChef(obj.getString("chef"));
		   vo.setFoodmake(obj.getString("foodmake"));
		   vo.setInfo1(obj.getString("info1"));
		   vo.setInfo2(obj.getString("info2"));
		   vo.setInfo3(obj.getString("info3"));
    */
   @RequestMapping(value="kotlin_recipe_detail.do",produces="text/plain;charset=UTF-8")
   public String recipe_kotlin_recipe_detail(int no)
   {
	   String result="";
	   try
	   {
		   RecipeDetailVO vo=rDao.recipeDetailData(no);
		   JSONObject obj=new JSONObject();
		   obj.put("title", vo.getTitle());
		   obj.put("poster", vo.getPoster());
		   obj.put("content", vo.getContent());
		   obj.put("foodmake", vo.getFoodmake());
		   obj.put("info1", vo.getInfo1());
		   obj.put("info2", vo.getInfo2());
		   obj.put("info3", vo.getInfo3());
		   
		   result=obj.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
   @RequestMapping(value="kotlin_chef_detail.do",produces="text/plain;charset=UTF-8")
   public String recipe_kotlin_chef_detail(String chef)
   {
	   String result="";
	   try
	   {
		   List<RecipeVO> list=rDao.chefDetailData(chef);
		   // JSON변환 []
		   JSONArray arr=new JSONArray();
		   for(RecipeVO vo:list)
		   {
			   //{}   
			   JSONObject obj=new JSONObject();
			   obj.put("chef", vo.getChef());
			   obj.put("title", vo.getTitle());
			   obj.put("poster", vo.getPoster());
			   arr.add(obj);
		   }
		   result=arr.toJSONString();
	   }catch(Exception ex){}
	   return result;
   }
   
}












