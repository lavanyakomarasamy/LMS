package com.newt.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newt.lms.model.Employee;
import com.newt.lms.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public Employee getEmployee(Employee employeeReq) {
		return employeeRepository.findOne(employeeReq.getEmployeeId());
	}
	
	public Employee createEmployee(Employee employeeReq) {
		System.out.println("===================="+new Date());
		System.out.println("=========DOB==========="+employeeReq.getDob());
		System.out.println("=========DOJ==========="+employeeReq.getDoj());
		
		
		employeeReq.setCreatedDate(new Date());
		employeeReq.setModifiedDate(new Date());
		return employeeRepository.save(employeeReq);
	}
	
}
