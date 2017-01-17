package com.imooc.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.dao.EmpDao;
import com.imooc.dao.LoginlogDao;
import com.imooc.entities.Emp;
import com.imooc.entities.Loginlog;
import com.imooc.exception.EmpExistException;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpDao empDao;
	@Autowired
	private LoginlogDao loginlogDao;

	@Override
	public Emp getEmpByEname(String ename) {
		return empDao.findByEname(ename);
	}

	@Override
	public void register(Emp emp) {
		Emp emp2 = empDao.findByEname(emp.getEname());
		empDao.deleteEmpById(7369);
		if(emp2 != null){
			throw new EmpExistException("用户名已经存在");
		}else{
			empDao.insertEmp(emp);
		}
	}

	@Override
	public Emp getEmpByEmpno(int empno) {
		return empDao.findById(empno);
	}

	@Override
	public List<Emp> getEmpLikeEname(String ename) {
		return empDao.findLikeEname(ename);
	}

	@Override
	public void loginSuccess(Emp emp) {
		emp.setCredit(5+emp.getCredit());
		Loginlog loginlog = new Loginlog();
		loginlog.setEmpno(emp.getEmpno());
		loginlog.setIp(emp.getLastip());
		loginlog.setLogin_datetime(new Date());
		empDao.updateEmp(emp);
		loginlogDao.insert(loginlog);
	}
	
	
	
	
	
}
