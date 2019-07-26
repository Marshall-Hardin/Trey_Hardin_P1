package com.revature.doa;

import java.sql.Connection;
import java.util.List;

import com.revature.beans.Employee;

public interface Employeedoa {
	
	Employee newEmployee(String username, String password, String email);
	Employee getEmployee(int id);
	Employee login(String username, String password);
	List<Employee> getEmployees();
	Employee updateEmployee(Employee employ);
	
}
