package com.tap.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.CustomerDaoImpl;
import com.tap.model.Customer;

@WebServlet("/SearchCustomerServlet")
public class SearchCustomerServlet  extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String Customer_columns = req.getParameter("Customer_columns");
		String CustomerInformation = req.getParameter("CustomerInformation");
		System.out.println("Customer_columns in sevlet :"+Customer_columns);
		System.out.println("Customer Inforamation in sevlet :"+CustomerInformation);
		if(Customer_columns.equals("firstName")) {
			CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
			List<Customer> allCustomer = customerDaoImpl.getCustomerByFirstName(CustomerInformation);
			session.setAttribute("allCustomer", allCustomer);
		}
		else if(Customer_columns.equals("city")){
			CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
			List<Customer> allCustomer = customerDaoImpl.getCustomerByCity(CustomerInformation);
			session.setAttribute("allCustomer", allCustomer);
		}
		else if(Customer_columns.equals("email")){
			CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
			List<Customer> allCustomer = customerDaoImpl.getCustomerByEmail(CustomerInformation);
			session.setAttribute("allCustomer", allCustomer);
		}
		else if(Customer_columns.equals("phone")){
			CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
			List<Customer> allCustomer = customerDaoImpl.getCustomerByPhone(CustomerInformation);
			session.setAttribute("allCustomer", allCustomer);
		}
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("displyAllCustomer.jsp");
		requestDispatcher.forward(req, resp);
		
	}
}
