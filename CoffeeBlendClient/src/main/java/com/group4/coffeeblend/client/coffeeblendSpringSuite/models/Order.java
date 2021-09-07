package com.group4.coffeeblend.client.coffeeblendSpringSuite.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.OrderDetail;
@Entity
@Table(name = "invoice")
public class Order implements Serializable {
	private static final long serialVersionUID = -5688758148089011103L;

	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name="invoice_status" , nullable = false)
	String invoiceStatus;
	
	@Column(name="customer_id" , nullable = false) 
	Integer customerID;
	
	@Column(name="customer_name" , nullable = false)
	String customerName;
	
	@Column(name="customer_address" , nullable = false)
	String customerAddress;
	
	@Column(name="customer_phone" , nullable = false)
	String customerPhone;
	
	@Column(name="total_cart_price" , nullable = false)
	Integer totalSpending;
	
	@Column(name="day_created" , nullable = false)
	Date dayCreated;
	
	@Column(name="day_updated" , nullable = true)
	Date dayUpdated;
	
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Integer getTotalSpending() {
		return totalSpending;
	}
	public void setTotalSpending(Integer totalSpending) {
		this.totalSpending = totalSpending;
	}
	public Date getDayCreated() {
		return dayCreated;
	}
	public void setDayCreated(Date dayCreated) {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(dayCreated);
		try{
			dayCreated = simpleDateFormat.parse(date);
		}catch( Exception e) {
			e.printStackTrace();
		}
		this.dayCreated = dayCreated;
	}
	public Date getDayUpdated() {
		return dayUpdated;
	}
	public void setDayUpdated(Date dayUpdated) {
		this.dayUpdated = dayUpdated;
	}
	public Set<OrderDetail> getOrderDetails() {
		// TODO Auto-generated method stub
		return orderDetails;
	}
	
}
