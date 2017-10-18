package com.newt.lms.model.jpa.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LeaveResponse {
	private String empId = null ;
	private ResponseStatus status = null;
	private Employee employee= null;
	
	public String getempId() {
		return empId;
	}
	public void setempId(String empId) {
		this.empId = empId;
	}
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
