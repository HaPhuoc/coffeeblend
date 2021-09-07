package com.group4.coffeeblend.client.coffeeblendSpringSuite.models;

import javax.persistence.Transient;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Product;

public class CartLinesInfo {
	private Product product;
	private int quantity;
	
	public CartLinesInfo() {
		this.quantity = 0;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Transient
	public Integer getAmount() {
		return product.getPrice() * quantity;
	}
}
