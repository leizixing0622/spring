package com.imooc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import com.imooc.entities.Emp;

import static com.imooc.cons.CommonConstant.*;

public class ForumFilter implements Filter {
	
	//定义一个全局标记，防止一个请求多次通过过滤器
	public static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	//不需要登录就能访问的url
	public static final String[] INHERENT_ESCAPE_URLS = {
		"/index.html","/index.jsp","/login.html","/login.jsp","/login/doLogin.html"
	};
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	//执行过滤方法
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//先检查有没有全局常量标记
		if(arg0 != null && arg0.getAttribute(FILTERED_REQUEST) != null){
			arg2.doFilter(arg0, arg1);
		}else{
			//给request一个标记
			arg0.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			//为了获取session中的emp,需要将servletrequest强转成httpservletrequest
			HttpServletRequest arg0Http = (HttpServletRequest) arg0;
			//获取session中保存的emp对象
			Emp emp = getSessionEmp(arg0Http);
			//1.没取到，检查是不是白名单里面的
			//getRequestURI从项目名字开始的全路径
			//getRequestURL从http开始的全路径
			if(emp == null & !isURLS(arg0Http.getRequestURI(),arg0Http)){
				System.out.println("还未登录");
				//将当前请求的全路径保存到session，以便登录后使用
				String fullUrl = arg0Http.getRequestURL().toString();
				//如果请求含get请求的话，也要一起保存下来
				if(!StringUtils.isEmpty(arg0Http.getQueryString())){
					fullUrl += "?" + arg0Http.getQueryString();
				}
				//保存到session中
				arg0Http.getSession().setAttribute(LOGIN_TO_URL, fullUrl);
				//转发到登录页面
				arg0.getRequestDispatcher("/login.jsp").forward(arg0Http, arg1);
				return;
			}
			arg2.doFilter(arg0Http, arg1);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	protected Emp getSessionEmp(HttpServletRequest request) {
		return (Emp) request.getSession().getAttribute(USER_CONTEXT);
	}
	
	//true表示属于白名单，false表示不属于白名单
	private boolean isURLS(String url,HttpServletRequest request){
		//getcontextpath，当前Web项目的名字
		if(request.getContextPath().equalsIgnoreCase(url) || (request.getContextPath() + "/").equalsIgnoreCase(url)){
			return true;
		}else{
			for(String a : INHERENT_ESCAPE_URLS){
				if(url != null && url.indexOf(a) >= 0){
					return true;
				}
			}
			return false;
		}
	}
}
