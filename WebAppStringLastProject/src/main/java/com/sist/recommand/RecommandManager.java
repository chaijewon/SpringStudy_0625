package com.sist.recommand;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodDetailVO;

import java.io.*;
@Component
public class RecommandManager {
   @Autowired
   private FoodDAO dao;
   public List<String> naverReplyData()
   {
	   List<String> rList=new ArrayList<String>();
	   try
	   {
		   JAXBContext jb=JAXBContext.newInstance(Rss.class);
		   Unmarshaller un=jb.createUnmarshaller();
		   // XML태그를 자바 객체로 변환 
		   Rss rss=(Rss)un.unmarshal(new File("c:\\food_recommand\\food.xml"));
		   List<Item> list=rss.getChannel().getItem();
		   String data="";
		   for(Item i:list)
		   {
			   rList.add(i.getDescription());
		   }
		  
	   }catch(Exception ex){}
	   return rList;
   }
   // 댓글 데이터를 받아서 맛집을 찾아서 브라우저,모바일에 전송 ==> 지능형 
   public List<FoodDetailVO> foodRecommandData()
   {
	   List<FoodDetailVO> list=new ArrayList<FoodDetailVO>();
	   List<FoodDetailVO> resultList = new ArrayList<FoodDetailVO>();
	   try
	   {
		   List<FoodDetailVO> dList=dao.foodHouseListData();
		   List<String> sList=naverReplyData();
		   int[] count=new int[dList.size()];// 맛집이 한개이상 추천시에 출력 
		   
		   // 패턴 , contains
		   for(String data:sList)
		   {
			   for(int i=0;i<dList.size();i++)
			   {
				   FoodDetailVO vo=dList.get(i);
				   if(data.contains(vo.getTitle()))
				   {
					   count[i]++;
				   }
			   }
		   }
		   
		   for(int i=0;i<count.length;i++)
		   {
			   if(count[i]!=0)
			   {
				   FoodDetailVO vo=dList.get(i);
				   list.add(vo);
				   System.out.println(vo.getTitle()+":"+count[i]);
			   }
		   }
		   
		   
          /* for (int i = 0; i < list.size(); i++) {
               if (!resultList.get(i).getTitle().contains(list.get(i).getTitle())) {
                   resultList.add(list.get(i));
               }
           }*/
	   }catch(Exception ex){}
	   return list;
   }
}







