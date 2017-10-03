package com.newt.lms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newt.lms.model.Login;
import com.newt.lms.repository.LoginRepository;
import com.newt.lms.repository.LoginSpec;

@Service
public class LoginService {
	
	@Autowired
	LoginRepository loginRepository;
	
	public List<Login> getLogin(Login loginReq) {
		return loginRepository.findAll(new LoginSpec(loginReq));
	}
	
	public Login createLogin(Login loginReq) {
		loginReq.setCreatedDate(new Date());
		loginReq.setModifiedDate(new Date());
		return loginRepository.save(loginReq);
	}
	
}
