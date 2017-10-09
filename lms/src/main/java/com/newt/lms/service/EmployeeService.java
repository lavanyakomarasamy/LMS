package com.newt.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newt.lms.jpa.repository.EmployeeRepository;
import com.newt.lms.mapper.ResourceMapper;
import com.newt.lms.model.jpa.dao.Employee;
import com.newt.lms.model.jpa.dto.EmployeeDTO;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ResourceMapper resourceMapper;
	
	public Employee getEmployee(Employee employeeReq) {
		return employeeRepository.findOne(employeeReq.getEmployeeId());
	}
	
	public Employee createEmployee(EmployeeDTO employeeDTO) {

		Date date = new Date();
		Employee employee = resourceMapper.convertEmployeeDTOToEmployeeDAO(employeeDTO);
		employee.setCreatedDate(date);
		employee.setModifiedDate(date);
		employee = employeeRepository.save(employee);

		return employee;
	}

}
