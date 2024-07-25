package com.tap.controller;

import java.awt.Cursor;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImpl.CustomerDaoImpl;
import com.tap.model.Customer;

@WebServlet("/CreatCustomer")
public class CreatCustomer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String firstName = req.getParameter("firstName");
//		String lastName = req.getParameter("lastName");
//		String street = req.getParameter("street");
//		String address = req.getParameter("address");
//		String city = req.getParameter("city");
//		String state = req.getParameter("state");
//		String email = req.getParameter("email");
//		String phone = req.getParameter("phone");
//		Customer customer = new Customer(firstName,lastName,street,address,city,state,email,phone);
		Customer customer = new Customer("tim","g","bengali","RamNagar","Kalaburagi","Karnataka","tim@gmail.com","8552737232");
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		customerDaoImpl.addCustomer(customer);
	}
}
