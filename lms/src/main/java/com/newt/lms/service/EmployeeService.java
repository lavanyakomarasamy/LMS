package com.newt.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newt.lms.model.jpa.dao.Employee;
import com.newt.lms.model.jpa.dto.EmployeeDTO;
import com.newt.lms.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public Employee getEmployee(Employee employeeReq) {
		return employeeRepository.findOne(employeeReq.getEmployeeId());
	}
	
	public Employee createEmployee(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee();
		Date date = new Date();
		employee.setDob(employeeDTO.getDob());
		employee.setDoj(employeeDTO.getDoj());
		employee.setCreatedDate(date);
		employee.setModifiedDate(date);
		employee.setDesignation(employeeDTO.getDesignation());
		employee.setDirectReporterId(employeeDTO.getDirectReporterId());
		employee.setEmployeeId(employeeDTO.getEmployeeId());
		employee.setEmployeeName(employeeDTO.getEmployeeName());
		employee.setMaritalStatus(employeeDTO.getMaritalStatus());
	
		employee = employeeRepository.save(employee);
		return employee;
	}
	
}
