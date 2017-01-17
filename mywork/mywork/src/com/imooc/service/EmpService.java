package com.imooc.service;

import java.util.List;

import com.imooc.entities.Emp;

public interface EmpService {

	public Emp getEmpByEname(String ename);
	public void register(Emp emp);
	public Emp getEmpByEmpno(int empno);
	public List<Emp> getEmpLikeEname(String ename);
	public void loginSuccess(Emp emp);
}
