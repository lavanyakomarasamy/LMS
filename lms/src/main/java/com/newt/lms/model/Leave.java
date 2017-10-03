package com.newt.lms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the leave database table.
 * 
 */
@Entity
@NamedQuery(name="Leave.findAll", query="SELECT l FROM Leave l")
public class Leave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="leave_id")
	private Integer leaveId;

	@Column(name="approved_by")
	private int approvedBy;

	@Column(name="approved_on")
	private Date approvedOn;

	@Column(name="casual_leaves")
	private int casualLeaves;

	private String comments;

	@Column(name="created_date")
	private Date createdDate;

	@Column(name="earnend_leaves")
	private int earnendLeaves;

	@Column(name="is_approved")
	private int isApproved;

	@Column(name="leave_balance")
	private int leaveBalance;

	@Column(name="leave_taken")
	private int leaveTaken;

	@Column(name="leave_type")
	private String leaveType;

	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="no_of_days")
	private int noOfDays;

	private String reason;

	@Column(name="requested_on")
	private Date requestedOn;

	@Column(name="requested_to")
	private String requestedTo;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="emp_id")
	private Employee employee;

	public Leave() {
	}

	public int getLeaveId() {
		return this.leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedOn() {
		return this.approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	public int getCasualLeaves() {
		return this.casualLeaves;
	}

	public void setCasualLeaves(int casualLeaves) {
		this.casualLeaves = casualLeaves;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getEarnendLeaves() {
		return this.earnendLeaves;
	}

	public void setEarnendLeaves(int earnendLeaves) {
		this.earnendLeaves = earnendLeaves;
	}

	public int getIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public int getLeaveBalance() {
		return this.leaveBalance;
	}

	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public int getLeaveTaken() {
		return this.leaveTaken;
	}

	public void setLeaveTaken(int leaveTaken) {
		this.leaveTaken = leaveTaken;
	}

	public String getLeaveType() {
		return this.leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getNoOfDays() {
		return this.noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getRequestedOn() {
		return this.requestedOn;
	}

	public void setRequestedOn(Date requestedOn) {
		this.requestedOn = requestedOn;
	}

	public String getRequestedTo() {
		return this.requestedTo;
	}

	public void setRequestedTo(String requestedTo) {
		this.requestedTo = requestedTo;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}