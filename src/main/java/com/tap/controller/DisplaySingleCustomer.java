package com.tap.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.CustomerDaoImpl;
import com.tap.model.Customer;

@WebServlet("/DisplayCustomer")
public class DisplaySingleCustomer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
//		int id = Integer.parseInt(req.getParameter("id"));
		Customer customer = customerDaoImpl.getCustomer(1);
		session.setAttribute("customer", customer);
		System.out.println(customer);

	}
}
