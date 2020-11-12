package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// MVC => Model (요청을 처리 => 결과값을 전송
import java.util.*;
import com.sist.dao.*;
@Controller
public class MainController {
    // DAO 객체 받기 
	@Autowired
	private StudentDAO dao;
	@RequestMapping("main/list.do")
	public String main_list(Model model)
	{
		List<StudentVO> list=dao.studentListData();
		model.addAttribute("list", list);
		return "list";
	}
}






