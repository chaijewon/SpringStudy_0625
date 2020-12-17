package com.sist.dao;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
@Repository
public class RecipeDAO {
   private MongoClient mc;
   private DB db;
   private DBCollection dbc;
   public RecipeDAO()
   {
	   try
	   {
		   mc=new MongoClient("localhost",27017);
		   db=mc.getDB("mydb");
		   dbc=db.getCollection("recipe");
	   }catch(Exception ex){}
   }
   public void recipeInsert(RecipeVO vo)
   {
	   try
	   {
		   BasicDBObject obj=new BasicDBObject();
		   obj.put("no", vo.getNo());
		   obj.put("title", vo.getTitle());
		   obj.put("poster", vo.getPoster());
		   obj.put("chef", vo.getChef());
		   obj.put("link", vo.getLink());
		   obj.put("hit", vo.getHit());
		   
		   dbc.insert(obj);
	   }catch(Exception ex){}
   }
   public void chefInsert(ChefVO vo)
   {
   	
   	try
   	{
   		BasicDBObject obj=new BasicDBObject();
   		obj.put("chef", vo.getChef());
   		obj.put("poster", vo.getPoster());
   		obj.put("mc1", vo.getMem_cont1());
   		obj.put("mc2", vo.getMem_cont2());
   		obj.put("mc3", vo.getMem_cont3());
   		obj.put("mc7", vo.getMem_cont7());
   		
   		dbc.insert(obj);
   	}catch(Exception ex){}
   }
   public void recipeDetailData(RecipeDetailVO vo)
   {
   	try
   	{
   		
   		BasicDBObject obj=new BasicDBObject();
   		obj.put("no", vo.getNo());
   		obj.put("poster", vo.getPoster());
   		obj.put("title", vo.getTitle());
   		obj.put("chef", vo.getChef());
   		obj.put("chef_poster", vo.getChef_poster());
   		obj.put("chef_profile", vo.getChef_profile());
   		obj.put("info1", vo.getInfo1());
   		obj.put("info2", vo.getInfo2());
   		obj.put("info3", vo.getInfo3());
   		obj.put("content", vo.getContent());
   		obj.put("foodmake", vo.getFoodmake());
   		
   		dbc.insert(obj);
   	}catch(Exception ex){}
   }
   // 레시피 데이터 읽기
   public List<RecipeVO> recipeListData(int page)
   {
	   dbc=db.getCollection("recipe");
	   List<RecipeVO> list=new ArrayList<RecipeVO>();
	   try
	   {
		   int rowSize=10;
		   int skip=(page*rowSize)-rowSize;
		   // new BasicDBObject("no",1):ASC , new BasicDBObject("no",-1):DESC
		   DBCursor cursor=dbc.find().skip(skip).limit(rowSize).sort(new BasicDBObject("no",1));
		   // find() => SELECT * FROM recipe
		   while(cursor.hasNext())// 데이터 읽은 갯수 만큼 ==> JSON => {} => BasicDBObject
		   {
			   // DBCursor == ResultSet
			   BasicDBObject obj=(BasicDBObject)cursor.next();
			   RecipeVO vo=new RecipeVO();
			   vo.setNo(obj.getInt("no"));
			   vo.setTitle(obj.getString("title"));
			   vo.setPoster(obj.getString("poster"));
			   vo.setChef(obj.getString("chef"));
			   vo.setHit(obj.getString("hit"));
			   list.add(vo);
		   }
		   cursor.close();
	   }catch(Exception ex){}
	   return list;
   }
   
   public List<ChefVO> chefListData(int page)
   {
	   dbc=db.getCollection("chef");
	   List<ChefVO> list=new ArrayList<ChefVO>();
	   try
	   {
		   int rowSize=10;
		   int skip=(page*rowSize)-rowSize;
		   // 전체데이터 읽기(ResultSet)
		   DBCursor cursor=dbc.find().skip(skip).limit(rowSize);
		   /*
		    *  {},{},{},{},{}....
		    *  === ROW(튜플) {} => 읽는 클래스 BasicDBObject
		    */
		   while(cursor.hasNext())//cursor에 저장된 갯수만큼 
		   {
			   BasicDBObject obj=(BasicDBObject)cursor.next();
			   ChefVO vo=new ChefVO();
			   vo.setChef(obj.getString("chef"));
			   /*
			    * {
					    "_id" : ObjectId("5fd9a005bd40ba313f684828"),
					    "chef" : "시크제이맘",
					    "poster" : "https://recipe1.ezmember.co.kr/cache/rpf/2016/01/19/3ebaebc5e49f53dd2f66b71932e5a33d1.jpg",
					    "mc1" : "1,709",
					    "mc2" : "21,402",
					    "mc3" : "1,006,425",
					    "mc7" : "32,735,239"
					}
			    */
			   vo.setPoster(obj.getString("poster"));
			   vo.setMem_cont1(obj.getString("mc1"));
			   vo.setMem_cont2(obj.getString("mc2"));
			   vo.setMem_cont3(obj.getString("mc3"));
			   vo.setMem_cont7(obj.getString("mc7"));
			   list.add(vo);
		   }
		   cursor.close();
	   }catch(Exception ex){}
	   return list;
   }
   public List<RecipeVO> chefDetailData(String chef)
   {
	   dbc=db.getCollection("recipe");
	   List<RecipeVO> list=new ArrayList<RecipeVO>();
	   try
	   {
		   BasicDBObject where=new BasicDBObject("chef",chef);
		   // WHERE chef='chef명'
		   DBCursor cursor=dbc.find(where).limit(20);
		   // limit=>가지고 오는 갯수 
		   while(cursor.hasNext())
		   {
			   BasicDBObject obj=(BasicDBObject)cursor.next();
			   RecipeVO vo=new RecipeVO();
			   vo.setChef(obj.getString("chef"));
			   vo.setTitle(obj.getString("title"));
			   vo.setPoster(obj.getString("poster"));
			   list.add(vo);
		   }
		   cursor.close();
	   }catch(Exception ex){}
	   return list;
   }
   /*
    *   private int no;
	   private String poster;
	   private String title;
	   private String chef;
	   private String chef_poster;
	   private String chef_profile;
	   private String info1,info2,info3;
	   private String content;
	   private String foodmake;
    */
   public RecipeDetailVO recipeDetailData(int no)
   {
	   RecipeDetailVO vo=new RecipeDetailVO();
	   try
	   {
		   BasicDBObject where=new BasicDBObject("no",no);
		   // WHERE no=1
		   BasicDBObject obj=(BasicDBObject)dbc.findOne(where);
		   // find(): 데이터 여러개 일때 , findOne() : 데이터 한개 정보
		   vo.setTitle(obj.getString("title"));
		   vo.setContent(obj.getString("content"));
		   vo.setPoster(obj.getString("poster"));
		   vo.setChef(obj.getString("chef"));
		   vo.setFoodmake(obj.getString("foodmake"));
		   vo.setInfo1(obj.getString("info1"));
		   vo.setInfo2(obj.getString("info2"));
		   vo.setInfo3(obj.getString("info3"));
	   }catch(Exception ex){}
	   return vo;
   }
   
}














