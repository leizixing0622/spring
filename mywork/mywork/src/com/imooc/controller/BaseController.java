package com.imooc.controller;

import javax.servlet.http.HttpServletRequest;

import com.imooc.cons.CommonConstant;
import com.imooc.entities.Emp;

public class BaseController {

	//���徲̬������
	protected static final String ERROR_MSG_KEY = "errorMsg";
	//��ȡSession�е�emp����
	protected Emp getSessionEmp(HttpServletRequest request){
		return (Emp) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}
	//����emp����session��
	protected void setSessionEmp(HttpServletRequest request,Emp emp){
		request.getSession().setAttribute(CommonConstant.USER_CONTEXT,emp);
	}
}
