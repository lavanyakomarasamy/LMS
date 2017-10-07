package com.newt.lms.model.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "created_date")
	private Date createdDate;

	private String designation;

	@Column(name = "direct_reporter_id")
	private Long directReporterId;

	private Date dob;

	private Date doj;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "modified_date")
	private Date modifiedDate;

	/*
	 * //bi-directional many-to-one association to Leave
	 * 
	 * @OneToMany(mappedBy="employee") private List<Leave> leaves;
	 * 
	 * //bi-directional many-to-one association to Login
	 * 
	 * @OneToMany(mappedBy="employee") private List<Login> logins;
	 */
	public Employee() {
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

	/*
	 * public List<Leave> getLeaves() { return this.leaves; }
	 * 
	 * public void setLeaves(List<Leave> leaves) { this.leaves = leaves; }
	 */

	/*
	 * public Leave addLeave(Leave leave) { getLeaves().add(leave);
	 * leave.setEmployee(this);
	 * 
	 * return leave; }
	 * 
	 * public Leave removeLeave(Leave leave) { getLeaves().remove(leave);
	 * leave.setEmployee(null);
	 * 
	 * return leave; }
	 * 
	 * public List<Login> getLogins() { return this.logins; }
	 * 
	 * public void setLogins(List<Login> logins) { this.logins = logins; }
	 * 
	 * public Login addLogin(Login login) { getLogins().add(login);
	 * login.setEmployee(this);
	 * 
	 * return login; }
	 * 
	 * public Login removeLogin(Login login) { getLogins().remove(login);
	 * login.setEmployee(null);
	 * 
	 * return login; }
	 */
}