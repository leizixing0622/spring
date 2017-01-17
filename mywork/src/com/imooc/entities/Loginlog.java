package com.imooc.entities;

import java.util.Date;

public class Loginlog {

	private int login_log_id;
	private int empno;
	private String ip;
	private Date login_datetime;
	public int getLogin_log_id() {
		return login_log_id;
	}
	public void setLogin_log_id(int login_log_id) {
		this.login_log_id = login_log_id;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLogin_datetime() {
		return login_datetime;
	}
	public void setLogin_datetime(Date login_datetime) {
		this.login_datetime = login_datetime;
	}
	public Loginlog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Loginlog(int login_log_id, int empno, String ip, Date login_datetime) {
		super();
		this.login_log_id = login_log_id;
		this.empno = empno;
		this.ip = ip;
		this.login_datetime = login_datetime;
	}
	@Override
	public String toString() {
		return "Loginlog [login_log_id=" + login_log_id + ", empno=" + empno
				+ ", ip=" + ip + ", login_datetime=" + login_datetime + "]";
	}
	
	
	
}
