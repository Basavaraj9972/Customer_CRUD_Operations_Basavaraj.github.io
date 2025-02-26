package com.tap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImpl.CustomerDaoImpl;

@WebServlet("/DeleteCustomer")
public class DeleteCustomer extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		boolean deleteCustomer = customerDaoImpl.deleteCustomer(id);
		System.out.println("Deleted customer ? :"+deleteCustomer);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("DisplayAllCustomer");
		requestDispatcher.forward(req, resp);
	}
}
