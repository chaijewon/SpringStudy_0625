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
   @Select("SELECT poster,title,no,score,addr,tel "
		  +"FROM food_detail "
		  +"WHERE cateno=#{cateno}")
   public List<FoodDetailVO> foodCategoryDetailData(int cateno);
   // 3. 맛집 상세보기 
   /*
    *   private int no,cateno,good,soso,bad;
        private String poster,title,score,addr,tel,type,price,menu;
    */
   @Select("SELECT * FROM food_detail "
		  +"WHERE no=#{no}")
   // MyBatis에 자동 구현 
   //     ============               ========
   // XML  resultType                parameterType
   public FoodDetailVO foodDetailData(int no);
   @Select("SELECT no,title,poster FROM food_detail ORDER BY no ASC")
   public List<FoodDetailVO> foodHouseListData();
   
   @Select("SELECT no,title,poster,chef,num "
		  +"FROM (SELECT no,title,poster,chef,rownum as num "
		  +"FROM (SELECT no,title,poster,chef "
		  +"FROM recipe ORDER BY no ASC)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<RecipeVO> recipeListData(Map map);
   
   @Select("SELECT chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7,num "
		  +"FROM (SELECT chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7,rownum as num "
		  +"FROM (SELECT chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7 "
		  +"FROM chef)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<ChefVO> chefListData(Map map);
}













