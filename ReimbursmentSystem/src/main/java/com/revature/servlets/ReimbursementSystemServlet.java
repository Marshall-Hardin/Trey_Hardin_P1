package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.dbDAOimpls.EmployeeImpl;
import com.revature.dbDAOimpls.ReimbursementImpl;
import com.revature.dbDAOimpls.dbConnectionHandler;
import com.revature.doa.Employeedoa;
import com.revature.doa.Reimbursementdao;


public class ReimbursementSystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Employeedoa employ = null;
	private Reimbursementdao reim = null;
	
	
	@Override
	public void init() throws ServletException {
		employ = new EmployeeImpl();
		reim = new ReimbursementImpl();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		System.out.println(path);
		ObjectMapper mapper = new ObjectMapper();
		Employee employee = null;
		ArrayList<Reimbursement> unfiltered = null;
		ArrayList<Reimbursement> filtered  = null;
		ArrayList<Employee> emps = null;
		int empId = -1;
		
		if(path.contains("/Project1/app/servlet/all-pending-manager/filtered")) {
			empId = Integer.parseInt(request.getParameter("empId"));
			path = "filtered";
		}
		
		switch(path) {
		
		/*Employee servlet operations*/	
		case "/Project1/app/servlet/info":
			employee = (Employee) request.getSession(false).getAttribute("employeebean");
			System.out.println("retrieving employee " + employee.getId());
			response.getWriter().print(mapper.writeValueAsString(employee));
			break;
			
		case "/Project1/app/servlet/pending-emp":
			employee = (Employee) request.getSession(false).getAttribute("employeebean");
			unfiltered = (ArrayList<Reimbursement>) reim.getReimbursement(false);
			filtered = new ArrayList<>();
			for (Reimbursement i: unfiltered) {
				if(i.getEmployeeId() == employee.getId()) {
					filtered.add(i);
				}
			}
			response.getWriter().print(mapper.writeValueAsString(filtered));
			break;
			
		/*Manager servlet operations*/
		case "/Project1/app/servlet/all-pending-manager":
			employee = (Employee) request.getSession(false).getAttribute("employeebean");
			unfiltered = (ArrayList<Reimbursement>) reim.getReimbursement(false);
			response.getWriter().print(mapper.writeValueAsString(unfiltered));
			break;
		
		case "filtered":
			employee = (Employee) request.getSession(false).getAttribute("employeebean");
			unfiltered = (ArrayList<Reimbursement>) reim.getReimbursement(false);
			filtered = new ArrayList<>();
			for (Reimbursement i: unfiltered) {
				if(i.getEmployeeId() == empId) {
					filtered.add(i);
					System.out.println(i.getreimbursementId());
				}
			}
			response.getWriter().print(mapper.writeValueAsString(filtered));
			break;
			
		case "/Project1/app/servlet/all-approved-manager" :
			employee = (Employee) request.getSession(false).getAttribute("employeebean");
			unfiltered = (ArrayList<Reimbursement>) reim.getReimbursement(true);
			response.getWriter().print(mapper.writeValueAsString(unfiltered));
			break;
			
		case "/Project1/app/servlet/all-employees-manager":
			emps = (ArrayList<Employee>) employ.getEmployees();
			response.getWriter().print(mapper.writeValueAsString(emps));
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		System.out.println(path);
		Employee employee = null;
		Reimbursement reimbursement = null;
		
		
		switch(path) {
		
		/*General use operations*/
		case "/Project1/app/servlet/login":
			employee = employ.login(request.getParameter("username"), request.getParameter("password"));
			request.getSession().setAttribute("employeebean", employee);
			if(employee != null) {
				if(!employee.isManager())
					response.sendRedirect("/Project1/employeehomepage.html");
				else
					response.sendRedirect("/Project1/managerhomepage.html");
			}
			else {
				response.sendRedirect("/Project1/");
				
			}
			break;
			
		case "/Project1/app/servlet/logout":
			request.getSession().invalidate();
			response.sendRedirect("/Project1/");
			break;
			
			
		/*Employee servlet operations*/	
		case "/Project1/app/servlet/editInfo":
			employee = (Employee) request.getSession(false).getAttribute("employeebean");
			employee.setFname(request.getParameter("fname"));
			employee.setLname(request.getParameter("lname"));
			employ.updateEmployee(employee);
			response.sendRedirect("/Project1/employeehomepage.html");
			break;
			
		case "/Project1/app/servlet/request":
			employee = (Employee) request.getSession(false).getAttribute("employeebean");
			reim.createReimbursement(Double.parseDouble(request.getParameter("amount")), employee.getId());
			response.sendRedirect("/Project1/submitRequest.html");
			break;
			
		/*Manager servlet operations*/
			
		case "/Project1/app/servlet/acceptRequest":
			reimbursement = reim.getReimbursement(Integer.parseInt(request.getParameter("reimId")));
			if(request.getParameter("accept-deny").contentEquals("accept")) {
				reimbursement.setApproved(true);
			}
			employee = (Employee) request.getSession(false).getAttribute("employeebean");
			reimbursement.setManagerId(employee.getId());
			reim.updateReimbursement(reimbursement);
			response.sendRedirect("/Project1/managerhomepage.html");
			break;

		}
		
	}
	@Override
	public void destroy() {
		try {
			dbConnectionHandler.getConnection().close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
