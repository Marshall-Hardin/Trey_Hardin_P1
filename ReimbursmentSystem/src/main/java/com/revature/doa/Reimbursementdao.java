package com.revature.doa;

import java.util.List;

//import com.revature.beans.Receipt;
import com.revature.beans.Reimbursement;

public interface Reimbursementdao {
	
	
	Reimbursement createReimbursement(double amount, int userId);
	Reimbursement getReimbursement(int id);
	List<Reimbursement> getReimbursement(boolean status);
	Reimbursement updateReimbursement(Reimbursement reim);
	
	//Receipt createReciept(int reimId, String blob);
	//List<Receipt> getReceipts> (int reimId);

}
