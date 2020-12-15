package com.sist.news;
import java.util.*;
/*
 *    Spring , MyBatis ==> 설정파일 (XML)
 *    XML 파싱법 (호환성 , 모든 운영체제에 같은 내용으로 파싱)
 *    ========
 *    jaxp :  Java API FOR XML Parser
 *            DOM (문서형 데이터베이스) => CURD
 *             => 단점 : 속도가 느리다 (메모리에 파싱한 데이터한 다음에 처리)
 *             => 장점 : 수정,추가 ,삭제 , 찾기 
 *            SAX (일반 프레임워크)=>Spring,MyBatis
 *             => 단점 : 읽기전용 
 *             => 속도가 빠르다 (태그를 한개씩 읽어서 처리)
 *    jaxb :  빅데이터,공공포털(OpenApi:XML,JSON) : 클래스와 XML태그를 매칭해서 처리 
 */
public class Channel {
   private List<Item> item=new ArrayList<Item>();

	public List<Item> getItem() {
		return item;
	}
	
	public void setItem(List<Item> item) {
		this.item = item;
	}
   
}
