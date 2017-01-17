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
	
	//����һ��ȫ�ֱ�ǣ���ֹһ��������ͨ��������
	public static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	//����Ҫ��¼���ܷ��ʵ�url
	public static final String[] INHERENT_ESCAPE_URLS = {
		"/index.html","/index.jsp","/login.html","/login.jsp","/login/doLogin.html"
	};
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	//ִ�й��˷���
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//�ȼ����û��ȫ�ֳ������
		if(arg0 != null && arg0.getAttribute(FILTERED_REQUEST) != null){
			arg2.doFilter(arg0, arg1);
		}else{
			//��requestһ�����
			arg0.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			//Ϊ�˻�ȡsession�е�emp,��Ҫ��servletrequestǿת��httpservletrequest
			HttpServletRequest arg0Http = (HttpServletRequest) arg0;
			//��ȡsession�б����emp����
			Emp emp = getSessionEmp(arg0Http);
			//1.ûȡ��������ǲ��ǰ����������
			//getRequestURI����Ŀ���ֿ�ʼ��ȫ·��
			//getRequestURL��http��ʼ��ȫ·��
			if(emp == null & !isURLS(arg0Http.getRequestURI(),arg0Http)){
				System.out.println("��δ��¼");
				//����ǰ�����ȫ·�����浽session���Ա��¼��ʹ��
				String fullUrl = arg0Http.getRequestURL().toString();
				//�������get����Ļ���ҲҪһ�𱣴�����
				if(!StringUtils.isEmpty(arg0Http.getQueryString())){
					fullUrl += "?" + arg0Http.getQueryString();
				}
				//���浽session��
				arg0Http.getSession().setAttribute(LOGIN_TO_URL, fullUrl);
				//ת������¼ҳ��
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
	
	//true��ʾ���ڰ�������false��ʾ�����ڰ�����
	private boolean isURLS(String url,HttpServletRequest request){
		//getcontextpath����ǰWeb��Ŀ������
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
