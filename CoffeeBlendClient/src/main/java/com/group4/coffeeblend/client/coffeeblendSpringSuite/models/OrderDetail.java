package com.group4.coffeeblend.client.coffeeblendSpringSuite.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_details")
public class OrderDetail implements Serializable {
	// nhớ set quantity trước đến price sau cùng là amount;
	/**********************/ 
	/*                    */ 											
	/*       Fields       */
	/*                    */ 											
	/**********************/
	private static final long serialVersionUID = 4601542882964429607L;

	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false, 
					foreignKey = @ForeignKey(name = "order_detail_ord_fk"))
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, 
				foreignKey = @ForeignKey(name = "order_detail_prod_fk"))
	private Product product;
	
	@Column(name ="amount" , nullable = false)
	private Double amount;
	
	@Column(name ="price" , nullable = false)
	private Double price;
	
	@Column(name ="quantity" , nullable = false)
	private Integer quantity;
	/***********************/ 
	/*                    */ 											
	/* Getters and Setters*/
	/*                    */ 											
	/**********************/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount() {
		this.amount = this.price*this.quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	} 
	
}
