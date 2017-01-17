package com.imooc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.entities.Emp;
import com.imooc.service.EmpService;

import static com.imooc.cons.CommonConstant.*;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private EmpService empService;
	
	@RequestMapping("/doLogin")
	public ModelAndView login(HttpServletRequest req,Emp emp){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forward:/login.jsp");
		//���mv���������ص�¼ҳ���
		//Ȼ������ύ���û���ȥ�����
		Emp emp2 = empService.getEmpByEname(emp.getEname());
		if(emp2 != null){
			if(!emp2.getPassword().equals(emp.getPassword())){
				mv.addObject("error", "�������");
			}else{
				emp2.setLastip(req.getRemoteAddr());
				empService.loginSuccess(emp2);
				//�ѵ�ǰ�û�����session����
				this.setSessionEmp(req, emp2);
				String toUrl = (String)req.getSession().getAttribute(LOGIN_TO_URL);
				req.getSession().removeAttribute(LOGIN_TO_URL);
				if(StringUtils.isEmpty(toUrl)){
					mv.setViewName("redirect:/index.jsp");
				}else{
					mv.setViewName("redirect:"+toUrl);
				}
			}
		}else{
			mv.addObject("error", "�û���������");
		}
		return mv;
	}
	
}
