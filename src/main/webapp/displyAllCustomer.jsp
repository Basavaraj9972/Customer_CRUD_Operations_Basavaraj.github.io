<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Customer,java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Customer Management</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #e0f7fa;
    margin: 0;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
}

table {
    border-collapse: collapse;
    width: 90%;
    margin: 20px 0;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table th, table td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: center;
}

table th {
    background-color: #4CAF50;
    color: white;
}

table tr:nth-child(even) {
    background-color: #f2f2f2;
}

table tr:hover {
    background-color: #ddd;
}

a {
    text-decoration: none;
    color: #333;
}

#buttons {
    margin-top: 20px;
}

#buttons a {
    margin: 0 10px;
}

#buttons input {
    font-size: 25px;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

#add {
    background-color: green;
    color: white;
}

#buttons input[type="button"]:nth-child(2) {
    background-color: green;
    color: white;
}

#buttons input:hover {
    opacity: 0.8;
}

.search-container {
    position: relative;
    display: flex;
    align-items: center;
    gap: 10px;
}

.search-container select,
.search-container input[type="text"],
.search-container input[type="submit"] {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.search-container input[type="text"] {
    padding-left: 35px; /* Add padding to leave space for the icon */
    width: 400px;
}

.search-container .fas {
    position: absolute;
    margin-top:15px;	
    left: 345px;
    color: #333;
}

#pagination {
    margin: 20px 0;
}

#pagination a, #pagination span {
    margin: 0 5px;
    text-decoration: none;
    color: #007bff;
}

#pagination span {
    font-weight: bold;
    color: #333;
}

</style>
</head>
<body>
    <%
    List<Customer> allCustomers = (List<Customer>) session.getAttribute("allCustomer");
    %>
    <div id="buttons">
        <div class="search-container">
        <a href="addCustomer.jsp"><input id="add" type="button" value="Add Customer"></a>
            <form action="SearchCustomerServlet" method="get">
                <select name="Customer_columns" required>
                    <option value="firstName">First Name</option>
                    <option value="city">City</option>
                    <option value="email">Email</option>
                    <option value="phone">Phone</option>
                </select>
                <input type="text" name="CustomerInformation" placeholder="Enter Customer details" required>
                <i class="fas fa-search"></i>
                <input type="submit" value="Search">
                <a href="SyncJSONCustomer"><input id="sync" type="button" value="Sync"></a>
            </form>
        </div>
    </div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Street</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Email</th>
            <th>Phone</th>
            <th colspan="2">Action</th>
        </tr>
        <%
        if (allCustomers != null) {
            for (Customer customer : allCustomers) {
        %>
        <tr>
            <td><%= customer.getFirstName() %></td>
            <td><%= customer.getLastName() %></td>
            <td><%= customer.getStreet() %></td>
            <td><%= customer.getAddress() %></td>
            <td><%= customer.getCity() %></td>
            <td><%= customer.getState() %></td>
            <td><%= customer.getEmail() %></td>
            <td><%= customer.getPhone() %></td>
            <td><a href="GetCustomerDetailsUpdate?id=<%=customer.getId()%>"> <img src="images/icons8-edit-50.png" height="20px" width="25px" alt="image not found"></a>
            <a href="DeleteCustomer?id=<%= customer.getId() %>"><img src="images/icons8-delete-100.png" height="20px" width="25px" alt="image not found"></a></td>
        </tr>
        <%
            }
        }
        %>
        <script>
			document.querySelector('form').addEventListener('submit', function(event) {
    		const phoneInput = document.querySelector('input[name="CustomerInformation"]');
    		const phonePattern = /^6\d{9}$/; // Starts with 6 and followed by exactly 9 digits

    		if (phoneInput.name === 'phone' && !phonePattern.test(phoneInput.value)) {
        		alert('Phone number must start with 6 and contain exactly 10 digits.');
        		event.preventDefault(); // Prevent form submission
    		}
		});
</script>
		 <div id="pagination">
        <%
        int currentPage = (Integer) request.getAttribute("currentPage");
        int totalPages = (Integer) request.getAttribute("totalPages");
        
        if (totalPages > 1) {
            for (int i = 1; i <= totalPages; i++) {
                if (i == currentPage) {
        %>
        <span><%= i %></span>
        <%
                } else {
        %>
        <a href="?pageNumber=<%= i %>"><%= i %></a>
        <%
                }
            }
        }
        %>
    </div>
        
    </table>
</body>
</html>
