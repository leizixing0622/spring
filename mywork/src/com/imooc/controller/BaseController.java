package com.imooc.controller;

import javax.servlet.http.HttpServletRequest;

import com.imooc.cons.CommonConstant;
import com.imooc.entities.Emp;

public class BaseController {

	//定义静态错误常量
	protected static final String ERROR_MSG_KEY = "errorMsg";
	//获取Session中的emp对象
	protected Emp getSessionEmp(HttpServletRequest request){
		return (Emp) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}
	//保存emp对象到session中
	protected void setSessionEmp(HttpServletRequest request,Emp emp){
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT,emp);
	}
}
