package com.tap.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetCustomerListJSON {
    private static final String AUTH_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
    private static final String CUSTOMER_LIST_URL = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
    private static final String LOGIN_ID = "test@sunbasedata.com";
    private static final String PASSWORD = "Test@123";
    public static ArrayList<Customer> list = null;
    public static void main(String[] args) {
        try {
            // Step 1: Authenticate to get the bearer token
            String token = authenticateAndGetToken();

            // Step 2: Use the token to get the customer list
            if (token != null && !token.isEmpty()) {
                List<Customer> customerList = getCustomerList(token);
                System.out.println("This is from ArrayList Customer");
                for(Customer customer :list) {
                	System.out.println(customer);
                }
                
                
            } else {
                System.out.println("Failed to obtain bearer token.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String authenticateAndGetToken() throws Exception {
        // Create the JSON body
        JSONObject body = new JSONObject();
        body.put("login_id", LOGIN_ID);
        body.put("password", PASSWORD);

        // Make the POST request
        URL url = new URL(AUTH_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Write the request body
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = body.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Read the response
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        // Print the response for debugging
        System.out.println("Authentication API Response: " + response.toString());

        // Parse the response to get the token
        JSONObject jsonResponse = new JSONObject(response.toString());

        // Adjust this based on actual response structure
        if (jsonResponse.has("access_token")) {
            return jsonResponse.getString("access_token");
        } else {
            System.out.println("Token not found in response.");
            return null;
        }
    }

    private static List<Customer> getCustomerList(String token) throws Exception {
        // Make the GET request
        URL url = new URL(CUSTOMER_LIST_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        // Check the response code
        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            // Read the response
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // Parse and print the response
            JSONArray jsonResponse = new JSONArray(response.toString());
            List<Customer> list = printCustomerList(jsonResponse);
        } else {
            System.out.println("GET request failed. Response Code: " + responseCode);
        }
        return list;
    }

    private static List<Customer> printCustomerList(JSONArray customerList) {
    		list = new ArrayList<Customer>();
    	
        for (int i = 0; i < customerList.length(); i++) {
            JSONObject customer = customerList.getJSONObject(i);
            Customer customer2 = new Customer(customer);
            list.add(customer2);
            
            System.out.println("UUID: " + customer.getString("uuid"));
            System.out.println("First Name: " + customer.getString("first_name"));
            System.out.println("Last Name: " + customer.getString("last_name"));
            System.out.println("Street: " + customer.getString("street"));
            System.out.println("Address: " + customer.getString("address"));
            System.out.println("City: " + customer.getString("city"));
            System.out.println("State: " + customer.getString("state"));
            System.out.println("Email: " + customer.getString("email"));
            System.out.println("Phone: " + customer.getString("phone"));
            System.out.println("-------------------------");
        }
        System.out.println("------------------------------");
        
        return list;
    }

}
