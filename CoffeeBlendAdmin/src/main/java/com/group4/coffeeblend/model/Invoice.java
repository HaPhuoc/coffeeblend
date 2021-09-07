package com.group4.coffeeblend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Invoice")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int customer_id;
	private String invoice_status;
	private String customer_name;
	private String customer_address;
	private String customer_phone;
	private Float total_cart_price;
	
	private java.sql.Date day_created;
	private Date day_updated;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getInvoice_status() {
		return invoice_status;
	}
	public void setInvoice_status(String invoice_status) {
		this.invoice_status = invoice_status;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public Float getTotal_cart_price() {
		return total_cart_price;
	}
	public void setTotal_cart_price(Float total_cart_price) {
		this.total_cart_price = total_cart_price;
	}
//	public Date getDay_created() {
//		return day_created;
//	}
//	public void setDay_created(Date day_created) {
//		this.day_created = day_created;
//	}
	public java.sql.Date getDay_created() {
		return day_created;
	}
	public void setDay_created(java.sql.Date day_created) {
		this.day_created = day_created;
	}
	
	public Date getDay_updated() {
		return day_updated;
	}
	public void setDay_updated(Date day_updated) {
		this.day_updated = day_updated;
	}
	
	
	
	

}
