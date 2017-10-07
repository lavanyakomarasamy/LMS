package com.newt.lms.model.jpa.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author nareshd
 *
 */
public class EmployeeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long employeeId;

	private Date createdDate;

	private String designation;

	private Long directReporterId;

	private Date dob;

	private Date doj;

	private String employeeName;

	private String maritalStatus;

	private Date modifiedDate;

	public EmployeeDTO() {
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getDirectReporterId() {
		return directReporterId;
	}

	public void setDirectReporterId(Long directReporterId) {
		this.directReporterId = directReporterId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}