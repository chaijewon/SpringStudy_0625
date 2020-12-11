package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
// 메모리 할당 
@Repository
public class MovieDAO {
   //  구현된 클래스의 메모리 주소 읽기
   @Autowired
   private MovieMapper mapper;
   public List<MovieVO> movieListData(Map map)
   {
	   return mapper.movieListData(map);
   }
  
   public int movieTotalPage(int cateno)
   {
	   return mapper.movieTotalPage(cateno);
   }
   // 상세보기 
   public MovieVO movieDetailData(int no)
   {
	   return mapper.movieDetailData(no);
   }
}
