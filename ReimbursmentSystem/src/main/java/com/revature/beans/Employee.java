package com.revature.beans;

public class Employee {
	
	private int id;
	private String username;
	private String password;
	private boolean manager;
	private String fname;
	private String lname;
	private String email;
	
	
	public Employee(int id, String username, String password, boolean manager, String fname, String lname,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.manager = manager;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}

	public Employee(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isManager() {
		return manager;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
