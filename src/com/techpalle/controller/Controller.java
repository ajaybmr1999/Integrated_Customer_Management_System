package com.techpalle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.AdminDao;
import com.techpalle.dao.CustomerDao;
import com.techpalle.model.Customer;

@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		switch(path) 
		{
		case "/list":
			validateAdmin(request, response);
			break;
		case "/delete":
			deleteCustomer(request,response);
		break;
		
		case "/edit":
			editCustomer(request, response);
			break;
			
		case "/editForm":
			getEditForm(request, response);
			break;
			
		case "/insertForm":
			getInsertForm(request, response);
			break;
			
		case "/add":
			customerInsert(request, response);
			break;
		
		case "/table":
			getCustomerListPage(request, response);
			break;
			default:
				getStartUpPage(request, response);
				break;
		}
	}
	
	private void getCustomerListPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList<Customer> alCustomer =  CustomerDao.getAllCustomers();
			
			RequestDispatcher rd =  request.getRequestDispatcher("customer_list.jsp");
			request.setAttribute("al", alCustomer);
			rd.forward(request, response);
		} 
		catch (ServletException e1) {
			e1.printStackTrace();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void validateAdmin(HttpServletRequest request, HttpServletResponse response) {
		// Read the data from admin.jsp file
		String e = request.getParameter("tbEmaillog");
		String p = request.getParameter("tbPasslog");
		
		//call DAO method to validate admin
		boolean res = AdminDao.validateAdmin(e, p);
		
		//condition to redirect to admin to list page
		if(res) {
			getCustomerListPage(request, response);			
		}
		else {
			try {
				response.sendRedirect(request.getContextPath()+"/default");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) {
		int i = Integer.parseInt(request.getParameter("id"));
		
		CustomerDao.deleteCustomerDetail(i);
		
		getCustomerListPage(request, response);
		
	}

	private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
		int i = Integer.parseInt(request.getParameter("tbId"));
		String n = request.getParameter("tbName");
		String e = request.getParameter("tbEmail");
		long m = Long.parseLong(request.getParameter("tbMobile"));
		
		Customer c = new Customer(i, n, e, m);
		
		CustomerDao.editCustomerDetails(c);
		
		getCustomerListPage(request, response);
	}

	private void getEditForm(HttpServletRequest request, HttpServletResponse response) {
		// Fetch the ID from the url:
		int i = Integer.parseInt(request.getParameter("id"));// here use id where we write href link that id should be write here
		
		Customer c =  CustomerDao.getOneCustomer(i);
		
		try {
			RequestDispatcher rd = request.getRequestDispatcher("customer_form.jsp");
			request.setAttribute("customer", c);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getInsertForm(HttpServletRequest request, HttpServletResponse response) {
	
		try {
			RequestDispatcher rd = request.getRequestDispatcher("customer_form.jsp");
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void customerInsert(HttpServletRequest request, HttpServletResponse response)
	{
		//Reading data customer-form page
		String n = request.getParameter("tbName");
		String e = request.getParameter("tbEmail");
		long m = Long.parseLong(request.getParameter("tbMobile"));
		
		//Store the admin given data into model/ Object
		Customer c = new Customer(n, e, m);
		
		// insert customer data to DB
		CustomerDao.insertCustomer(c);
		
		//redirect to admin to homepage (customer-list page)
		getCustomerListPage(request, response);
		
	}

	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			RequestDispatcher rd =  request.getRequestDispatcher("admin_login.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
