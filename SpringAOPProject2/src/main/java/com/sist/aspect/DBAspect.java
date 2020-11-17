package com.sist.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
 *   @Aspect => 공통모듈 => DAO에서 공통사용 내용을 모아 준다(메모리 할당은 하지 않는다)
     @Component => 메모리 할당
 */
import com.sist.dao.*;
/*
 *    public List<EmpVO> empListData()
 *    {
 *         try
 *         {
 *             getConnection() ==> Before
 *             ------- SQL => 전송 => 결과값 => List,VO
 *             -------
 *             -------
 *         }catch(Exception e)
 *         {
 *             e.printStackTrace() After-Throwing
 *         }
 *         finally
 *         {
 *             disConnection() => After
 *         }
 *         
 *    }
 *    
 *    자동 호출 
 *    ===============================
 *    1. 언제 ===> PointCut
 *    2. 어디서 ==> JoinPoint
 *    =============================== Advice
 *    Advice => 여러개 => Aspect
 */
@Aspect
@Component
public class DBAspect {
   @Autowired
   private DBConnection dbConn;
   
   @Before("execution(* com.sist.dao.EmpDAO.emp*(..))")
   public void before()
   {
	   dbConn.getConnection();
   }
   @After("execution(* com.sist.dao.EmpDAO.emp*(..))")
   public void after()
   {
	   dbConn.disConnection();
   }
}













