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
		// Get pageNumber parameter, default to 1 if not provided
		String pageNumberParam = req.getParameter("pageNumber");
		int pageNumber = (pageNumberParam != null && !pageNumberParam.isEmpty()) ? Integer.parseInt(pageNumberParam): 1;

		// Get pageSize parameter, default to 10 if not provided
		String pageSizeParam = req.getParameter("pageSize");
		int pageSize = (pageSizeParam != null && !pageSizeParam.isEmpty()) ? Integer.parseInt(pageSizeParam) : 10;

		CustomerDaoImpl dao = new CustomerDaoImpl();
		List<Customer> allCustomers = dao.getCustomers(pageNumber, pageSize);
		System.out.println("All Customers " + allCustomers);
		int totalRecords = dao.getTotalRecords();
		int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
		System.out.println("totalPages " + totalPages);
		System.out.println("pageNumber " + pageNumber);
		session.setAttribute("allCustomer", allCustomers);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("currentPage", pageNumber);

		RequestDispatcher dispatcher = req.getRequestDispatcher("displyAllCustomer.jsp");
		dispatcher.forward(req, resp);

	}

}
