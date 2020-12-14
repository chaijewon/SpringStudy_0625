package com.sist.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// Main페이지에서 서비스 역할 
/*
 *    @Controller
 *    =============  forward , sendRedirect만 담당 
 *       forward를 하기 위해서는 jsp파일명을 보낸다 
 *       return "경로명/jsp파일명" => request를 유지하기 위해서 사용
 *       sendRedirect
 *       return "redirect:경로명/.do" => request가 필요없는 경우 
 *    @RestController
 *    =============== 해당 JSP로 문자열을 전송할 경우 
 *                    JSON,XML => React,Ajax,코틀린 (데이터만 전송)
 *                                ================
 *    Web : 브라우저에서 실행 (클라이언트)
 *    App : 모바일 처리 (클라이언트)
 *    =================== 서버 (Spring , NodeJS , Asp.net)
 *                           ========  =======
 *                                     NodeJS+Redux
 *    1. 맛집 : Spring + JSP + 코틀린 
 *    ==============================
 *    2. 레시피 : NodeJS+Redux(React)
 *    ==============================
 *    
 *    ==> 포트폴리오 : 완전한 사이트(X) => 기능 사용 
 *        
 */
import java.util.*;
import com.sist.dao.*;
@Controller
// web=>Spring을 이용해서 web프로그램 제작 
@RequestMapping("food/")
public class MainController {
   // DAO의 객체를 받는다 
   @Autowired
   private FoodDAO dao;
   
   @RequestMapping("list.do")
   public String food_category(String no,Model model)
   {
	   // Model => request로 변환 (ViewResolver)
	   if(no==null) // 첫화면 => default 
		   no="1";
	   int in=Integer.parseInt(no);
	   int start=0;
	   int end=0;
	   if(in==1)
	   {
		   start=1;
		   end=12;
	   }
	   else if(in==2)
	   {
		   start=13;
		   end=18;
	   }
	   else if(in==3)
	   {
		   start=19;
		   end=30;
	   }
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end",end);
	   List<FoodCategoryVO> list=dao.foodCategoryData(map);
	   model.addAttribute("list", list);// JSP에서 출력 
	   
	   return "list";
   }
}




















