package com.revature.beans;

public class Reimbursement {
	
	private int reimbursementId;
	private int employeeId;
	private int managerId;
	private double reimbursementAmount;
	private boolean approved;
	
	public Reimbursement(int reimbursementId, int employeeId, int managerId, double reimbursementAmount,
			boolean approved) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.reimbursementAmount = reimbursementAmount;
		this.approved = approved;
	}

	public Reimbursement(int employeeId, double reimbursementAmount) {
		super();
		this.employeeId = employeeId;
		this.reimbursementAmount = reimbursementAmount;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public double getreimbursementAmount() {
		return reimbursementAmount;
	}

	public void setreimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getreimbursementId() {
		return reimbursementId;
	}
	
	public void setreimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

}
