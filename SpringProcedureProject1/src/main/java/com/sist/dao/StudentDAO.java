package com.sist.dao;

import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;

import java.util.*;
import java.sql.*;
@Repository
public class StudentDAO {
   private Connection conn;
   private CallableStatement cs;
   private final String URL="jdbc:oracle:thin:@211.238.142.181:1521:XE";
   /*
    *  PreparedStatement : 일반 SQL문장 전송 
    *  CallableStatement : Procedure를 호출 
    */
   // 드라이버 등록
   public StudentDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
	   }catch(Exception ex){}
   }
   // 연결
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
	   }catch(Exception ex){}
   }
   // 해제
   public void disConnection()
   {
	   try
	   {
		   if(cs!=null) cs.close();
		   if(conn!=null) conn.close();
	   }catch(Exception ex){}
   }
   // 기능 
   /*
    *       CREATE OR REPLACE PROCEDURE studentListData(
			   pResult OUT SYS_REFCURSOR
			)
			IS
			BEGIN
			   OPEN pResult FOR
			     SELECT * FROM pl_student;
			END;
			/
    */
   public List<StudentVO> studentListData()
   {
	   List<StudentVO> list=new ArrayList<StudentVO>();
	   try
	   {
		   getConnection();
		   String sql="{CALL studentListData(?)}";
		   cs=conn.prepareCall(sql);
		   cs.registerOutParameter(1, OracleTypes.CURSOR);
		   //   int ==> OracleTypes.INTEGER
		   //   String => OracleTypes.VARCHAR
		   //   double => OracleTypes.DOUBLE
		   //   Date  => OracleTypes.DATE
		   //   ResultSet => OracleTypes.CURSOR
		   // 실행 
		   cs.executeQuery();
		   ResultSet rs=(ResultSet)cs.getObject(1);
		   while(rs.next())
		   {
			   StudentVO vo=new StudentVO();
			   vo.setHakbun(rs.getInt(1));
			   vo.setName(rs.getString(2));
			   vo.setKor(rs.getInt(3));
			   vo.setEng(rs.getInt(4));
			   vo.setMath(rs.getInt(5));
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   disConnection();
	   }
	   return list;
   }
   
}










