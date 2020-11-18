package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
public interface BoardMapper {
   @Select("SELECT no,subject,name,regdate,hit,gt,num "
		  +"FROM (SELECT no,subject,name,regdate,hit,gt,rownum as num "
		  +"FROM (SELECT no,subject,name,regdate,hit,gt "
		  +"FROM srBoard ORDER BY gi DESC,gs ASC)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<BoardVO> boardListData(Map map);
   
   @SelectKey(keyProperty="no",resultType=int.class,before=true,
		   statement="SELECT NVL(MAX(no)+1,1) as no FROM srBoard")
   @Insert("INSERT INTO srBoard(no,name,subject,content,pwd,gi) "
		  +"VALUES(#{no},#{name},#{subject},#{content},#{pwd},"
		  +"(SELECT NVL(MAX(gi)+1,1) FROM srBoard))")
   public void boardInsert(BoardVO vo);
   
   // 상세보기 
   @Update("UPDATE srBoard SET "
		  +"hit=hit+1 "
		  +"WHERE no=#{no}")
   public void boardHitIncrement(int no);
   
   @Select("SELECT no,name,subject,content,regdate,hit "
		  +"FROM srBoard "
		  +"WHERE no=#{no}")
   public BoardVO boardDetailData(int no);
}









