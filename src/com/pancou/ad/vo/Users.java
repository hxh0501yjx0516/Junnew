package com.pancou.ad.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * user实体类
 * @author 
 *
 */
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2129786655339161827L;
	private int userId ;
	private String username; //用户名
	private String pwd;
	private String realname; //真实名
	private String dept; //部门
	private String title; //职务
	private String tel ;  //电话
	private String lastlogtime;  //最后登录的时间
	private String lastip ;  //访问的ip
	private int state ; //1 有效
	private String sid;
	
	//private Set<Role> userRoles = new HashSet<Role>();
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLastlogtime() {
		return lastlogtime;
	}
	public void setLastlogtime(String lastlogtime) {
		this.lastlogtime = lastlogtime;
	}
	public String getLastip() {
		return lastip;
	}
	public void setLastip(String lastip) {
		this.lastip = lastip;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
}
