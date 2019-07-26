package com.revature.dbDAOimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.doa.Employeedoa;

public class EmployeeImpl implements Employeedoa {
	
	private Connection con;
	
	public EmployeeImpl() {
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
	public Employee newEmployee(String username, String password, String email) {
		//1. passed connection in via constructor
		try {
		 //2. Create a statement.
			String sql = "INSERT INTO employees(username, password, email)"
					+"VALUES (?, ?, ?)";
			String[] primaryKeyValues = {"employ_id"};
			PreparedStatement stmt = con.prepareStatement(sql, primaryKeyValues);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, email);
        
			//3. Executing the statement
			stmt.executeUpdate();
        
			//4. get id
			ResultSet keys = stmt.getGeneratedKeys();
			Employee emp = new Employee(username, password,email);
			while(keys.next()) 
			{
				int userId = keys.getInt(1);
				emp.setId(userId);
			}
			return emp;
		} 
		catch (SQLException e) 
        {
            //would probably want to throw a custom application-specific exception to be handled elsewhere.
            //System.out.println("Something went wrong while trying to create a car in the database.");
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public Employee getEmployee(int id) {
		try {
			String sql = "Select * From employees Where employ_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet result = stmt.executeQuery();
			Employee emp = null;
			while(result.next()) {
				String uname = result.getString("username");
				String pword = result.getString("password");
				boolean manager = result.getInt("is_manager")==1;
				String fname = result.getString("first_name");
				String lname = result.getString("last_name");
				String email = result.getString("email");
				
				emp = new Employee(id, uname, pword, manager, fname, lname, email);
			}
			return emp;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee login(String username, String password) {
		try {
			String sql = "Select * From employees Where username = ? And password = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet result = stmt.executeQuery();
			Employee emp = null;
			while(result.next()) {
				int id = result.getInt("employ_id");
				boolean manager = result.getInt("is_manager")==1;
				String fname = result.getString("first_name");
				String lname = result.getString("last_name");
				String email = result.getString("email");
				
				emp = new Employee(id, username, password, manager, fname, lname, email);
			}
			return emp;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Employee> getEmployees() {
		try {
			String sql = "Select * From employees";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet result = stmt.executeQuery();
			List<Employee> empList = new ArrayList<>();
			while(result.next()) {
				int id = result.getInt("employ_id");
				String uname = result.getString("username");
				String pword = result.getString("password");
				boolean manager = result.getInt("is_manager")==1;
				String fname = result.getString("first_name");
				String lname = result.getString("last_name");
				String email = result.getString("email");
				
				empList.add(new Employee(id, uname, pword, manager, fname, lname, email));
			}
			return empList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee updateEmployee(Employee employ) {
		
		int ismanager = 0;
		
		if(employ.isManager()) {
			ismanager = 1;
		}
		try{
			 String sql = "UPDATE employees SET username = ?, password = ?, is_manager = ?,"
					 + " first_name = ?, last_name = ?, email = ? WHERE employ_id = ?";
			 PreparedStatement stmt = con.prepareStatement(sql);
			 stmt.setString(1, employ.getUsername());
			 stmt.setString(2, employ.getPassword());
			 stmt.setInt(3, ismanager);
			 stmt.setString(4, employ.getFname());
			 stmt.setString(5, employ.getLname());
			 stmt.setString(6, employ.getEmail());
			 stmt.setInt(7, employ.getId());
			
			 stmt.executeUpdate();
			 
			 return employ;
			 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
