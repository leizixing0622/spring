package com.imooc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imooc.entities.Emp;
import com.imooc.service.EmpService;

@Controller
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	private EmpService empservice;
	
	@RequestMapping("/findById")
	public String findById(){
		Emp emp = empservice.getEmpByEmpno(7369);
		return "emp/findById";
	}
}
