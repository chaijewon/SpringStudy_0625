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
   
}














