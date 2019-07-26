package com.revature.beans;

public class Receipt {
	private int receptId;
	//private ??? blob;
	private int reimbursmentId;
	
	public Receipt(int receptId,/* ??? blob,*/ int reimbursmentId) {
		super();
		this.receptId = receptId;
		//this.blob = blob;
		this.reimbursmentId = reimbursmentId;
	}

	/*public Receipt(int receptId, int reimbursmentId) {
		super();
		this.receptId = receptId;
		this.reimbursmentId = reimbursmentId;
	}*/

	public int getReceptId() {
		return receptId;
	}

	public int getReimbursmentId() {
		return reimbursmentId;
	}
	
	

}
