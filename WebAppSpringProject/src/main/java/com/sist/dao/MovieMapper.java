package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import java.util.*;
public interface MovieMapper {
   // 목록 => 페이지 나누기
   @Select("SELECT no,cateno,title,poster,score,regdate,num "
		  +"FROM (SELECT no,cateno,title,poster,score,regdate,rownum as num "
		  +"FROM (SELECT no,cateno,title,poster,score,regdate "
		  +"FROM daum_movie WHERE cateno=#{cateno})) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<MovieVO> movieListData(Map map);
   @Select("SELECT CEIL(COUNT(*)/12.0) FROM daum_movie WHERE cateno=#{cateno}")
   public int movieTotalPage(int cateno);
   // 상세보기 
   @Select("SELECT * FROM daum_movie WHERE no=#{no}")
   public MovieVO movieDetailData(int no);
}
