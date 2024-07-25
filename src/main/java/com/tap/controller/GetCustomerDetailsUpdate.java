package com.tap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.CustomerDaoImpl;
import com.tap.model.Customer;

@WebServlet("/GetCustomerDetailsUpdate")
public class GetCustomerDetailsUpdate extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("id is "+id);
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		Customer customer = customerDaoImpl.getCustomer(id);
		System.out.println("Customer is "+customer);
		session.setAttribute("customer", customer);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("UpdateCustomer.jsp");
		requestDispatcher.forward(req, resp);
	}
}
