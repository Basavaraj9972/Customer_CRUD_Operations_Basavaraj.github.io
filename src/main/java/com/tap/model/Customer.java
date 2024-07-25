package com.tap.model;

import org.json.JSONObject;

public class Customer {
	private int id;
	private String uuid;
	private String firstName;
    private String lastName;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;
    
    public Customer() {

    }
    
    
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	
	public Customer(int id, String uuid, String firstName, String lastName, String street, String address, String city,
			String state, String email, String phone) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
	}


	public Customer(String uuid, String firstName, String lastName, String street, String address, String city,
			String state, String email, String phone) {
		super();
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
	}
	
	 public Customer(JSONObject jsonObject) {
	        this.uuid = jsonObject.getString("uuid");
	        this.firstName = jsonObject.getString("first_name");
	        this.lastName = jsonObject.getString("last_name");
	        this.street = jsonObject.getString("street");
	        this.address = jsonObject.getString("address");
	        this.city = jsonObject.getString("city");
	        this.state = jsonObject.getString("state");
	        this.email = jsonObject.getString("email");
	        this.phone = jsonObject.getString("phone");
	    }




	public Customer(String firstName, String lastName, String street, String address, String city, String state,
			String email, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
	}




	public Customer(int id, String firstName, String lastName, String street, String address, String city, String state,
			String email, String phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", uuid=" + uuid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", street=" + street + ", address=" + address + ", city=" + city + ", state=" + state + ", email="
				+ email + ", phone=" + phone + "]";
	}


}
