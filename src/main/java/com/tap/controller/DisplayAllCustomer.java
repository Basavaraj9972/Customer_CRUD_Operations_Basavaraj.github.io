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

@WebServlet("/DisplayAllCustomer")
public class DisplayAllCustomer extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> allCustomer = customerDaoImpl.getAllCustomer();
		System.out.println(allCustomer);
		session.setAttribute("allCustomer", allCustomer);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("displyAllCustomer.jsp");
		requestDispatcher.forward(req, resp);
	}

}
