package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
// 스프링에서 메모리 할당 => 구현한 Mapper의 주소를 넘겨준다 
/*
 *    스프링에서 메모리 할당 요청 => 싱글턴을 사용하기 때문에 메모리를 절약 할 수 있다 
 *    메모리 할당을 하는 어노테이션 (구분)
 *    
 *    @Component : 일반 클래스 (Jsoup,뉴스읽기,OpenApi)
 *    @Repository : 데이터베이스 관련 => DAO
 *    @Service : DAO가 여러개 일때 묶어서 사용 (BI)
 *    @Controller : Model(MVC)
 *    @RestController: Model(MVC)
 */
@Repository
public class FoodDAO {
   // 스프링에서 구현 클래스를 받는다 
   @Autowired
   private FoodMapper mapper;
   public List<FoodCategoryVO> foodCategoryData(Map map)
   {
	   return mapper.foodCategoryData(map);
   }
   public List<FoodDetailVO> foodCategoryDetailData(int cateno)
   {
	   return mapper.foodCategoryDetailData(cateno);
   }
}












