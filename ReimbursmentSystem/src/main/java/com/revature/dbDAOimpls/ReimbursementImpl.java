package com.revature.dbDAOimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.doa.Reimbursementdao;

public class ReimbursementImpl implements Reimbursementdao {
	
	private Connection con;
	
	public ReimbursementImpl() {
		try {
			con = dbConnectionHandler.getConnection();
			System.out.println("we have a connection");
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println("we have a problem");
		}
	}

	@Override
	public Reimbursement createReimbursement(double amount, int userId) {
		try {
			 //2. Create a statement.
				String sql = "INSERT INTO REIMBURSEMENTS (employ_id, reimbursement)"
						+"VALUES (?, ?)";
				String[] primaryKeyValues = {"reimbursements_id"};
				PreparedStatement stmt = con.prepareStatement(sql, primaryKeyValues);
				stmt.setInt(1, userId);
				stmt.setDouble(2, amount);
	        
				//3. Executing the statement
				stmt.executeUpdate();
	        
				//4. get id
				ResultSet keys = stmt.getGeneratedKeys();
				Reimbursement reim = new Reimbursement(userId, amount);
				while(keys.next()) 
				{
					int reimId = keys.getInt(1);
					reim.setreimbursementId(reimId);
				}
				return reim;
		} 
		
		catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	    }
	}

	@Override
	public Reimbursement getReimbursement(int id) {
		try {
			String sql = "Select * from REIMBURSEMENTS Where reimbursements_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet results = stmt.executeQuery();
			Reimbursement reim = null; 
			
			while(results.next()) {
				int employId = results.getInt("employ_id");
				int managerId = results.getInt("manager_id");
				double amount = results.getDouble("reimbursement");
				boolean approved = results.getInt("approved")==1;
				reim = new Reimbursement(id, employId, managerId, amount, approved);
			}
			
			return reim;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reimbursement> getReimbursement(boolean status) {
		
		int approve = 0;
		
		if(status) {
			approve = 1;
		}
		
		try {
			String sql = "Select * From REIMBURSEMENTS Where approved = " + approve;
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet results = stmt.executeQuery();
			List<Reimbursement> reim = new ArrayList<>(); 
			
			while(results.next()) {
				int id = results.getInt("reimbursements_id");
				int employId = results.getInt("employ_id");
				int managerId = results.getInt("manager_id");
				double amount = results.getDouble("reimbursement");
				boolean approved = results.getInt("approved")==1;
				reim.add(new Reimbursement(id, employId, managerId, amount, approved));
			}
			
			return reim;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reim) {
		
		int approved = 0;
		if(reim.isApproved()) {
			approved = 1;
		}
		
		try {
			String sql = "Update reimbursements Set manager_id = ?, approved = ? Where reimbursements_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, reim.getManagerId());
			stmt.setInt(2, approved);
			stmt.setInt(3, reim.getreimbursementId());
			
			stmt.executeUpdate();
			
			return reim;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
