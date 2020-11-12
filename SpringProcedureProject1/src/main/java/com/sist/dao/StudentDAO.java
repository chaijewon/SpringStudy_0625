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
			   vo.setAvg(rs.getDouble(6));
			   vo.setTotal(rs.getInt(7));
			   
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   try
		   {
			   if(cs!=null) cs.close();
			   if(conn!=null) conn.close();
		   }catch(Exception ex){}
	   }
	   return list;
   }
   /*
    *   CREATE OR REPLACE PROCEDURE studentInsert(
		   pName pl_student.name%TYPE,
		   pKor pl_student.kor%TYPE,
		   pEng pl_student.eng%TYPE,
		   pMath pl_student.math%TYPE
		)
		IS
		BEGIN
		   INSERT INTO pl_student VALUES(
		     (SELECT NVL(MAX(hakbun)+1,1) FROM pl_student),
		     pName,pKor,pEng,pMath
		   );
		   COMMIT;
		END;
		/
    */
   public void studentInsert(StudentVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="{CALL studentInsert(?,?,?,?)}";
		   cs=conn.prepareCall(sql);
		   cs.setString(1, vo.getName());
		   cs.setInt(2, vo.getKor());
		   cs.setInt(3, vo.getEng());
		   cs.setInt(4, vo.getMath());
		   cs.executeQuery();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   disConnection();
	   }
   }
   
   /*
    *  CREATE OR REPLACE PROCEDURE studentDelete(
		   pNo pl_student.hakbun%TYPE
		)
		IS
		BEGIN
		  DELETE FROM pl_student
		  WHERE hakbun=pNo;
		  COMMIT;
		END;
		/
    */
   public void studentDelete(int hakbun)
   {
	   try
	   {
		   getConnection();
		   String sql="{CALL studentDelete(?)}";
		   cs=conn.prepareCall(sql);
		   cs.setInt(1, hakbun);
		   cs.executeQuery();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   disConnection();
	   }
   }
   /*
		   CREATE OR REPLACE PROCEDURE studentDetailData(
		   pNo NUMBER,
		   pName OUT VARCHAR2,
		   pKor OUT NUMBER,
		   pEng OUT NUMBER,
		   pMath OUT NUMBER
		)
		IS 
		BEGIN
		  SELECT name,kor,eng,math INTO pName,pKor,pEng,pMath
		  FROM pl_student
		  WHERE hakbun=pNo;
		END;
		/
    */
   public StudentVO studentDetailData(int hakbun)
   {
	   StudentVO vo=new StudentVO();
	   try
	   {
		   getConnection();
		   String sql="{CALL studentDetailData(?,?,?,?,?)}";
		   cs=conn.prepareCall(sql);
		   cs.setInt(1, hakbun);
		   cs.registerOutParameter(2, OracleTypes.VARCHAR);// name
		   cs.registerOutParameter(3, OracleTypes.INTEGER);// kor
		   cs.registerOutParameter(4, OracleTypes.INTEGER);// eng
		   cs.registerOutParameter(5, OracleTypes.INTEGER);// math
		   cs.executeQuery();
		   vo.setName(cs.getString(2));
		   vo.setKor(cs.getInt(3));
		   vo.setEng(cs.getInt(4));
		   vo.setMath(cs.getInt(5));
		   vo.setHakbun(hakbun);
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   disConnection();
	   }
	   return vo;
   }
   /*
    *   CREATE OR REPLACE PROCEDURE studentUpdate(
		   pNo NUMBER,
		   pName VARCHAR2,
		   pKor NUMBER,
		   pEng NUMBER,
		   pMath NUMBER
		)
		IS
		  -- 변수
		BEGIN
		  UPDATE pl_student SET
		  name=pName,kor=pKor,eng=pEng,math=pMath
		  WHERE hakbun=pNo;
		  COMMIT;
		END;
		/
    */
   public void studentUpdate(StudentVO vo)
   {
	   try
	   {
		   getConnection();
		   String sql="{CALL studentUpdate(?,?,?,?,?)}";
		   cs=conn.prepareCall(sql);
		   cs.setInt(1, vo.getHakbun());
		   cs.setString(2, vo.getName());
		   cs.setInt(3, vo.getKor());
		   cs.setInt(4, vo.getEng());
		   cs.setInt(5, vo.getMath());
		   cs.executeQuery();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   disConnection();
	   }
   }
}










