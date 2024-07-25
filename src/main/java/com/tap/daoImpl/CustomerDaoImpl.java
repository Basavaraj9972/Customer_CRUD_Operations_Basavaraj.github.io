package com.tap.daoImpl;
import com.tap.daoInf.CustomerDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.model.Customer;

public class CustomerDaoImpl implements CustomerDao{
	
	private Connection con;

    public CustomerDaoImpl() {
		try {
			String url = "jdbc:mysql://localhost:3306/customer_task3";
			String username = "root";
			String password = "root";
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver class is loded CustomerApplication ");
			con = DriverManager.getConnection(url, username,password);
			System.out.println("Data base connectivity is established CustomerApplication");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO `customers` (`firstName`, `lastName`, `street`, `address`, `city`, `state`, `email`, `phone`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        try {
        	PreparedStatement statement = con.prepareStatement(sql);
        	statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getStreet());
			statement.setString(4, customer.getAddress());
			statement.setString(5, customer.getCity());
			statement.setString(6, customer.getState());
			statement.setString(7, customer.getEmail());
			statement.setString(8, customer.getPhone());
			rowInserted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return rowInserted;
    }
    
    @Override
    public boolean addCustomerJSON(Customer customer) {
    	String sql = "INSERT INTO `customers` (`uuid`,`firstName`, `lastName`, `street`, `address`, `city`, `state`, `email`, `phone`) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        try {
        	PreparedStatement statement = con.prepareStatement(sql);
        	statement.setString(1, customer.getUuid());
        	statement.setString(2, customer.getFirstName());
			statement.setString(3, customer.getLastName());
			statement.setString(4, customer.getStreet());
			statement.setString(5, customer.getAddress());
			statement.setString(6, customer.getCity());
			statement.setString(7, customer.getState());
			statement.setString(8, customer.getEmail());
			statement.setString(9, customer.getPhone());
			rowInserted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return rowInserted;
    }

    

    @Override
    public List<Customer> getAllCustomer(){
        List<Customer> listCustomer = new ArrayList<>();
        try {
        	String sql = "SELECT * FROM `customers`";
        	Statement statement = con.createStatement();
        	ResultSet resultSet;
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String uuid = resultSet.getString("uuid");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String street = resultSet.getString("street");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String email = resultSet.getString("email");
				String phone = resultSet.getString("phone");
				Customer customer = new Customer(id,uuid,firstName, lastName, street, address, city, state, email, phone);
				listCustomer.add(customer);
			}
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return listCustomer;
    }

    @Override
    public boolean deleteCustomer(int id) {
        String sql = "DELETE FROM `customers` where `id` = ?";
        boolean rowDeleted = false;
        try {
        	PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return rowDeleted;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE `customers` SET `firstName` = ?, `lastName` = ?, `street` = ?, `address` = ?, `city` = ?, `state` = ?, `email` = ?, `phone` = ?";
        sql += " WHERE id = ?";
        boolean rowUpdated = false;
        try {
        	PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getStreet());
			statement.setString(4, customer.getAddress());
			statement.setString(5, customer.getCity());
			statement.setString(6, customer.getState());
			statement.setString(7, customer.getEmail());
			statement.setString(8, customer.getPhone());
			statement.setInt(9, customer.getId());
			rowUpdated = statement.executeUpdate() > 0;
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return rowUpdated;
    }

    @Override
    public Customer getCustomer(int id) {
        Customer customer = null;
        String sql = "SELECT * FROM `customers` WHERE `id` = ?";
        try {
        	PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				String uuid = resultSet.getString("uuid");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String street = resultSet.getString("street");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String email = resultSet.getString("email");
				String phone = resultSet.getString("phone");
				customer = new Customer(id,uuid,firstName, lastName, street, address, city, state, email, phone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return customer;
    }

	@Override
	public List<Customer> getCustomerByFirstName(String firstName) {
		List<Customer> listCustomer = new ArrayList<Customer>();
		try {
			String sql = "SELECT * FROM `customers` where `firstName`=?";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, firstName);
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String lastName = resultSet.getString("lastName");
				String street = resultSet.getString("street");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String email = resultSet.getString("email");
				String phone = resultSet.getString("phone");
				Customer customer = new Customer(id,firstName, lastName, street, address, city, state, email, phone);
				listCustomer.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCustomer;
	}

	@Override
	public List<Customer> getCustomerByCity(String city) {
		List<Customer> listCustomer = new ArrayList<>();
		try {
			String sql = "SELECT * FROM `customers` where `city`=?";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, city);
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String street = resultSet.getString("street");
				String address = resultSet.getString("address");
				String state = resultSet.getString("state");
				String email = resultSet.getString("email");
				String phone = resultSet.getString("phone");
				Customer customer = new Customer(id,firstName, lastName, street, address, city, state, email, phone);
				listCustomer.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCustomer;
	}

	@Override
	public List<Customer> getCustomerByEmail(String email) {
		List<Customer> listCustomer = new ArrayList<>();
		try {
			String sql = "SELECT * FROM `customers` where `email`=?";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, email);
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String street = resultSet.getString("street");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String phone = resultSet.getString("phone");
				Customer customer = new Customer(id,firstName, lastName, street, address, city, state, email, phone);
				listCustomer.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCustomer;
	}

	@Override
	public List<Customer> getCustomerByPhone(String phone) {
		List<Customer> listCustomer = new ArrayList<>();
		try {
			String sql = "SELECT * FROM `customers` where `phone`=?";
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, phone);
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String street = resultSet.getString("street");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				String state = resultSet.getString("state");
				String email = resultSet.getString("email");
				Customer customer = new Customer(id,firstName, lastName, street, address, city, state, email, phone);
				listCustomer.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCustomer;
	}

    
	// Example: CustomerDAO.java

//	public List<Customer> getCustomers(int pageNumber, int pageSize) {
//	    List<Customer> customers = new ArrayList<>();
//	    int offset = (pageNumber - 1) * pageSize;
//	    
//	    String sql = "SELECT * FROM customers LIMIT ? OFFSET ?";
//	    
//	    try {
//	    	PreparedStatement stmt = con.prepareStatement(sql);
//	        stmt.setInt(1, pageSize);
//	        stmt.setInt(2, offset);
//	        ResultSet resultSet = stmt.executeQuery();
//	        	while (resultSet.next()) {
//					int id = resultSet.getInt("id");
//					String uuid = resultSet.getString("uuid");
//					String firstName = resultSet.getString("firstName");
//					String lastName = resultSet.getString("lastName");
//					String street = resultSet.getString("street");
//					String address = resultSet.getString("address");
//					String city = resultSet.getString("city");
//					String state = resultSet.getString("state");
//					String email = resultSet.getString("email");
//					String phone = resultSet.getString("phone");
//					Customer customer = new Customer(id,uuid,firstName, lastName, street, address, city, state, email, phone);
//					customers.add(customer);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return customers;
//	}
//
//	public int getTotalRecords() {
//	    int totalRecords = 0;
//	    String sql = "SELECT COUNT(*) FROM customers";
//	    
//	    try {
//	    	PreparedStatement stmt = con.prepareStatement(sql);
//	        ResultSet rs = stmt.executeQuery();
//	        if (rs.next()) {
//	            totalRecords = rs.getInt(1);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    
//	    return totalRecords;
//	}

	
	
//    public List<Customer> getAllCustomer(int page, int size, String sortField, String sortOrder, String searchQuery) {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers WHERE CONCAT(firstName, ' ', lastName) LIKE ? ORDER BY " 
//                     + sortField + " " + sortOrder 
//                     + " LIMIT ? OFFSET ?";
//        try {
//             PreparedStatement ps = con.prepareStatement(sql);
//             ps.setString(1, "%" + searchQuery + "%");
//             ps.setInt(2, size);
//             ps.setInt(3, (page - 1) * size);
//	             ResultSet rs = ps.executeQuery();
//             while (rs.next()) {
//                Customer customer = new Customer();
//                customer.setId(rs.getInt("id"));
//                customer.setFirstName(rs.getString("firstName"));
//                customer.setLastName(rs.getString("lastName"));
//                customer.setStreet(rs.getString("street"));
//                customer.setAddress(rs.getString("address"));
//                customer.setCity(rs.getString("city"));
//                customer.setState(rs.getString("state"));
//                customer.setEmail(rs.getString("email"));
//                customer.setPhone(rs.getString("phone"));
//                customers.add(customer);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return customers;
//    }
//    
    
//    @Override
//    public List<Customer> getAllCustomer(int page, int size, String sortField, String sortOrder, String searchQuery) {
//        List<Customer> customers = new ArrayList<>();
//        String sql = "SELECT * FROM customers WHERE firstName LIKE ? OR lastName LIKE ? OR email LIKE ? " +
//                     "ORDER BY " + sortField + " " + sortOrder + " LIMIT ? OFFSET ?";
//        
//        try {
//             PreparedStatement stmt = con.prepareStatement(sql);
//             stmt.setString(1, "%" + searchQuery + "%");
//             stmt.setString(2, "%" + searchQuery + "%");
//             stmt.setString(3, "%" + searchQuery + "%");
//             stmt.setInt(4, size);
//             stmt.setInt(5, (page - 1) * size);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    Customer customer = new Customer();
//                    customer.setId(rs.getInt("id"));
//                    customer.setFirstName(rs.getString("firstName"));
//                    customer.setLastName(rs.getString("lastName"));
//                    customer.setEmail(rs.getString("email"));
//                    customer.setPhone(rs.getString("phone"));
//                    customers.add(customer);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
	
//        }
//        return customers;
//    }

}
