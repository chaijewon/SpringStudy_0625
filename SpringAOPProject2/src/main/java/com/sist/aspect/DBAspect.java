package com.sist.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
 *    
 *    Proxy 
 *    
 *    public class A
 *    {
 *       public void display()
 *       {
 *          System.out.println("A Call...");
 *       }
 *    }
 *    
 *    public class Proxy
 *    {
 *        A a;
 *        public Proxy(A a)
 *        {
 *           this.a=a;
 *        }
 *        public void display()
 *        {
 *           System.out.println("Before");
 *           a.display();
 *           System.out.println("After");
 *        }
 *    }
 *    
 *    A a=new A();
 *    Proxy p=new Proxy(a);
 *    p.display();
 *    
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
   /*
    *    Around
    *    =======
    *      1. 자동 호출 setAutoCommit(false)
    *      == 핵심 코딩  SQL 문장 수행
    *      2. 자동 호출 commit()
    */
   @Around("execution(* com.sist.web.EmpController.*(..))")
   public Object around(ProceedingJoinPoint jp) throws Throwable
   {
	   Object obj=null;
	   System.out.println("사용자 호출전:"+jp.getSignature().getName());
	   obj=jp.proceed();// 메소드 호출 
	   System.out.println("사용자 호출후:"+jp.getSignature().getName());
	   return obj;
   }
   @AfterReturning(value="execution(* com.sist.web.EmpController.*(..))",returning="val")
   public void afterReturning(JoinPoint jp,Object val)
   {
	   System.out.println("리턴값:"+val);
   }
   
   @AfterThrowing(value="execution(* com.sist.web.EmpController.*(..))",throwing="ex")
   public void afterThrowing(Throwable ex)
   {
	   System.out.println(ex.getMessage());
   }
}













