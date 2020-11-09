package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// Model => JSP(HTML(태그형)+Java)
// JSP(HTML(태그형) => VIEW
// JAVA => Model (1. VO,2.DAO , 3.Manager)
// Controller 스프링안에서는 제작 
// => 작동 (매뉴얼 제작 => XML)
// 이 클래스는 => Model역할
import java.util.*;
import com.sist.manager.*;
@Controller
public class MovieController {
	@Autowired
    private MovieManager mgr;
	@RequestMapping("movie/main.do")
	public String movie_main(int no,Model model)
	{
		
		List<MovieVO> list=mgr.jsonAllData(no);
		model.addAttribute("list", list);
		return "movie/main";
	}
}













