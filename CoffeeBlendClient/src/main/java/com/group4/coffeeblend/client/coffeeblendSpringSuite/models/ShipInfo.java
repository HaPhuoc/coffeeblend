package com.group4.coffeeblend.client.coffeeblendSpringSuite.models;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.services.CustomerServices;

public class ShipInfo {
	private int customerId;
	private String name;
	private String address;
	private String email;
	@Size(min = 9 ,max=10)
	private String phone;
	
	private String district;
	private String ward;
	
	@Autowired
	CustomerServices repo = new CustomerServices();
	
	public ShipInfo() { 
		super();
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public int getCustomerId() {
		//if(repo.getByEmail(email) != null)  return repo.getByEmail(email).getId();
		return 0;
		
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		//if(address == null || this.ward ==null || this.district ==null) this.address= "";
		//else 
		this.address = address;
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
	
}
