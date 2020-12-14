package com.sist.dao;
import java.util.*;
// mybatis ==> XML버전,어노테이션 (메소드를 선언하면 => 자동으로 구현)
/*
 *   ${vo.no>=1 && vo.no<=12 }  믿고 보는 맛집 리스트
 *   ${vo.no>=13 && vo.no<=18 } 지역별 인기 맛집
 *   ${vo.no>=19 && vo.no<=30 } 메뉴별 인기 맛집
 */
import org.apache.ibatis.annotations.Select;
public interface FoodMapper {
   // 1. Category 데이터를 넘겨준다 
   // @Select => <select> 
   @Select("SELECT no,title,subject,poster "
		  +"FROM food_category "
		  +"WHERE no BETWEEN #{start} AND #{end}")
   public List<FoodCategoryVO> foodCategoryData(Map map);
   // 2. Category별 맛집 찾기
   // 3. 맛집 상세보기 
}
