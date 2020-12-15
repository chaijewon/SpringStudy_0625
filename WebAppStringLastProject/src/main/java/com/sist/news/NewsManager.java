package com.sist.news;

import org.springframework.stereotype.Component;
/*
 *   @Controller : web (화면 변경,이동)
 *   @RestController : 다른 프로그램과 연동 
 *                     데이터를 모아서 => JavaScript,모바일
 *                     =========== XML,JSON
 *   @Repository : DAO,데이터베이스 관련 
 *   @Service : BI (DAO여러개를 묶어서 사용,Manager가 여러개)
 *   ===================== @Component사용 
 */
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.net.*;//웹을 연결 => 인코딩 URLEncoder
/*
 *    Collection : 자료구조 (데이터 저장)
 *    => 1. 자바에서는 표준화
 *    => 저장 방법에 따라 클래스가 분리
 *       List               Set      Map => interface
 *      =====
 *       = ArrayList
 *       = Vector
 *       = LinkedList(C개발자)
 *       ==================== 순서(index),중복저장이 가능
 *       
 *       
 */
@Component("mgr")
// id="mgr" ==> id="newsManager"
// <bean id="mgr" ~~/>
public class NewsManager {
   public List<Item> newsListData(String fd)
   {
	   List<Item> list=new ArrayList<Item>();
	   try
	   {
		   // URL연결 => 데이터 받기 => 파싱 => 전송 
		   String strUrl="http://newssearch.naver.com/search.naver?where=rss&"
				        +"query="+URLEncoder.encode(fd, "UTF-8");
		   URL url=new URL(strUrl);
		   
		   // 파싱
		   JAXBContext jb=JAXBContext.newInstance(Rss.class);
		   //                                  받을 클래스명(@XMLRootElement)
		   Unmarshaller un=jb.createUnmarshaller();
		   /*
		    *   Unmarshaller : XML=>자바클래스로 변환
		    *   Marshaller : 자바클래스 => XML로 변환 
		    *   
		    */
		   Rss rss=(Rss)un.unmarshal(url);
		   list=rss.getChannel().getItem();
	   }catch(Exception ex){}
	   return list;
   }
}














