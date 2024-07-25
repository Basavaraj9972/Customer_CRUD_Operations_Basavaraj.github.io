package com.tap.daoInf;

import java.util.List;

import com.tap.model.Customer;

public interface CustomerDao {
	public boolean addCustomer(Customer customer);
	public List<Customer> getAllCustomer();
	public boolean deleteCustomer(int id);
	public boolean updateCustomer(Customer customer);
	public Customer getCustomer(int id);
	public List<Customer> getCustomerByFirstName(String firstName);
	public List<Customer> getCustomerByCity(String city);
	public List<Customer> getCustomerByEmail(String email);
	public List<Customer> getCustomerByPhone(String phone);
//	 List<Customer> getAllCustomer(int page, int size, String sortField, String sortOrder, String searchQuery);
}
