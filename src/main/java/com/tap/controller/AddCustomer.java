package com.tap.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImpl.CustomerDaoImpl;
import com.tap.model.Customer;
@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String street = req.getParameter("street");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		Customer customer = new Customer(firstName,lastName,street,address,city,state,email,phone);
//		Customer customer = new Customer(3,"tim Rocky","cook","Btm","Btm Layout","Bengaluru","Karnataka","tim@gmail.com","8552737232");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		boolean addCustomer = customerDaoImpl.addCustomer(customer);
		System.out.println("AddedCustomer ? :"+addCustomer);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("DisplayAllCustomer");
		requestDispatcher.forward(req, resp);
	}

}
