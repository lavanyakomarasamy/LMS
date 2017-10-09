package com.newt.lms.model.jpa.dao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LoginResponse {
	private String userId = null ;
	private ResponseStatus status = null;
	private Login login= null;
	private List<Login> loginList= null;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login order) {
		this.login = order;
	}
	public List<Login> getLoginList() {
		return loginList;
	}
	public void setLoginList(List<Login> loginList) {
		this.loginList = loginList;
	}
	
}
