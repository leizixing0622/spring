package com.imooc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imooc.entities.Emp;

public interface EmpDao {
	@Insert("insert into emp (empno,ename,job,mgr,hiredate,sal,comm,deptno,credit,lastip,password) values (#{empno},#{ename},#{job},#{mgr},#{hiredate},#{sal},#{comm},#{deptno},#{credit},#{lastip},#{password})")
	public void insertEmp(Emp emp);
	@Delete("delete from emp where empno=#{empno}")
	public void deleteEmpById(int empno);
	@Update("update emp set ename = #{ename},job = #{job},mgr = #{mgr},hiredate = #{hiredate},sal = #{sal},comm = #{comm},deptno = #{deptno},credit = #{credit},lastip=#{lastip},password=#{password} where empno = #{empno}")
	public void updateEmp(Emp emp);
	@Select("select * from emp where empno=#{empno}")
	public Emp findById(int empno);
	@Select("select * from emp where ename=#{ename}")
	public Emp findByEname(String ename);
	@Select("select *from emp where ename like concat(concat('%',#{ename}),'%')")
	public List<Emp> findLikeEname(String ename);
	
	public List<Emp> selectPage(Emp emp); 
}
