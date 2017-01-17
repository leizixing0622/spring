package com.imooc.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.imooc.dao.EmpDao;
import com.imooc.dao.TopicDao;
import com.imooc.entities.Emp;
import com.imooc.entities.Page;
import com.imooc.entities.Topic;
import com.imooc.service.EmpService;
import com.imooc.service.ForumService;

public class test {
	
	 ApplicationContext app = null;
	 EmpDao empDao = null;
	 TopicDao topicDao = null;
	 EmpService empService = null;
	 ForumService forumService = null;
	 
	 @Before
	 public void before(){
		 
		app = new ClassPathXmlApplicationContext("applicationContext.xml");
		empDao = (EmpDao) app.getBean("empDao");
		topicDao = (TopicDao)app.getBean("topicDao");
		forumService =  (ForumService) app.getBean("forumServiceImpl");
	 }
	 
	@Test
	public void testMybatis() {
		System.out.println(forumService.getPagedTopics(1,1,1).toString());
	}
	
	@Test
	public void testInsert() {
		Emp emp = empDao.findByEname("Allen");
        empService.loginSuccess(emp);
		System.out.println("ok");
	}
	
	@Test
	public void testPage(){
		Emp u=new Emp();
		u.setPage_begin(2);
		u.setPage_size(3);
		System.out.println("-u.getCount()------"+u.getCount());
		List<Emp> l=empDao.selectPage(u);
		System.out.println(l);
		System.out.println("-u.getCount()------"+u.getCount());
		for (Emp a : l ){
			System.out.println(a);
			System.out.println();
		}
	}
	@Test
	public void testPage2(){
		Topic u=new Topic();
		u.setPage_begin(0);
		u.setPage_size(2);
		u.setBoard_id(1);
		List<Topic> l= topicDao.selectPage(u);
		System.out.println(l);
	}

}
