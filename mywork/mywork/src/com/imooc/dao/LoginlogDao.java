package com.imooc.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imooc.entities.Emp;
import com.imooc.entities.Loginlog;

public interface LoginlogDao {

		//增
		@Insert("insert into loginlog (login_log_id,empno,ip,login_datetime) values (#{login_log_id},#{empno},#{ip},#{login_datetime})")
		public void insert(Loginlog loginlog);
		//删
		@Delete("delete from loginlog where login_log_id=#{login_log_id}")
		public void delete(int login_log_id);
		//改
		@Update("update loginlog set login_log_id = ${login_log_id},empno = ${empno},ip = ${ip},login_datetime = ${login_datetime} where login_log_id = ${login_log_id}")
		public void update(Loginlog loginlog);
		//查
		@Select("select * from loginlog where login_log_id=#{login_log_id}")
		public Emp findById(int login_log_id);
	
}
