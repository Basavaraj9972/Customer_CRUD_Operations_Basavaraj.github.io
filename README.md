Hello, This Customer CRUD Operation Project so you need to create database name is `customer_task3` and tale name is `customers` 
table structure is 
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` char(36) NOT NULL DEFAULT (uuid()),
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `street` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `city` varchar(250) NOT NULL,
  `state` varchar(45) NOT NULL,
  `email` varchar(150) NOT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_UNIQUE` (`uuid`)
)
for database connectivity username is `root` and password is `root` 
excute the index file .
This project will do the 
Authenticate and Retrieve a Token:
and pagination also done.
The servlet makes a POST request to an authentication URL (AUTH_URL) with login credentials (LOGIN_ID and PASSWORD) in JSON format.
It retrieves a JSON response containing an access_token.
Use the Token to Fetch Customer Data:

The servlet uses the retrieved access_token to make a GET request to a customer list URL (CUSTOMER_LIST_URL).
The request is authenticated using the Bearer token in the Authorization header.
Process and Display Customer Data:

The servlet processes the JSON response containing a list of customers.
It constructs an HTML response with customer details (such as UUID, name, address, email, and phone) and sends it back to the client.
