<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
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

form {
    background-color: #fff;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 50%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}

label {
    margin-top: 10px;
    font-weight: bold;
}

input[type="text"], input[type="email"], input[type="tel"] {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    border: 1px solid #ddd;
    border-radius: 4px;
}

input[type="submit"], input[type="button"] {
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

input[type="button"] {
    background-color: #f44336;
}

input[type="submit"]:hover, input[type="button"]:hover {
    opacity: 0.8;
}

.buttons {
    display: flex;
    justify-content: space-between;
    width: 100%;
}
</style>
</head>
<body>
	<form action="AddCustomer" method="post">
        <input type="hidden" name="id">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required>

        <label for="street">Street:</label>
        <input type="text" id="street" name="street"  required>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address"  required>

        <label for="city">City:</label>
        <input type="text" id="city" name="city"  required>

        <label for="state">State:</label>
        <input type="text" id="state" name="state"  required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email"  required>

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone"  required>

        <div class="buttons">
            <input type="submit" value="Add Customer">
            <input type="button" value="Cancel" onclick="window.location.href='displyAllCustomer.jsp'">
        </div>
    </form>
</body>
</html>