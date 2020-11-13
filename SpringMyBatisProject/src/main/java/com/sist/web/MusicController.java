package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.dao.*;
/*
 *     public class ViewResolver
 *     {
 *       private String prefix;
 *       private String suffix;
 *                              music/list
 *       public String jspChange(String re)
 *       {
 *          return prefix+re+suffix;
 *                 /music/list.jsp
 *       }
 *     } 
 *     
 *     public class Model
 *     {
 *        HttpServletRequest request;
 *        public void addAttribute(String key,Object obj)
 *        {
 *            request.setAttribute(key,obj);
 *        }
 *     }
 */
@Controller
public class MusicController {
   @Autowired
   private MusicDAO dao;
   // GET , POST (form,ajax)
   @GetMapping("music/list.do")
   public String music_list(String page,Model model)
   {
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=10;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   map.put("start", start);
	   map.put("end", end);
	   List<MusicVO> list=dao.musicListData(map);
	   
	   // 전송 
	   model.addAttribute("list", list);
	   return "music/list";//ViewResolver prefix="/"리턴값suffix=".jsp"
   }
}










