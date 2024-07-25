package com.tap.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImpl.CustomerDaoImpl;
import com.tap.model.Customer;
import com.tap.model.GetCustomerListJSON;

@WebServlet("/SyncJSONCustomer")
public class SyncJSONCustomer extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GetCustomerListJSON getCustomerListJSON = new GetCustomerListJSON();
		String authenticateAndGetToken;
		List<Customer> customerList = null;
		try {
			authenticateAndGetToken = GetCustomerListJSON.authenticateAndGetToken();
			customerList = GetCustomerListJSON.getCustomerList(authenticateAndGetToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		List<Customer> allCustomer = customerDaoImpl.getAllCustomer();
		if(customerList!=null && allCustomer!=null) {
			for(int i =0;i<customerList.size();i++) {
				for( int j=0;j<allCustomer.size();j++) {
					if(!(customerList.get(i).getUuid()).equals(allCustomer.get(j).getUuid())) {
						boolean customerJSON2 = customerDaoImpl.addCustomerJSON(customerList.get(i));
						System.out.println("customerJSON2 is added ? :"+customerJSON2);
					}
				}
			}	
		}
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("DisplayAllCustomer");
		requestDispatcher.forward(req, resp);
	}
}
