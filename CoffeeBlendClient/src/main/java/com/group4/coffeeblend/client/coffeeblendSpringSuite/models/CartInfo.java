package com.group4.coffeeblend.client.coffeeblendSpringSuite.models;

import java.util.ArrayList;
import java.util.List;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.CartInfo;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.CartLinesInfo;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Product;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.ShipInfo;

public class CartInfo {
	private Integer orderNumber; 	//ma don hang
	
	private ShipInfo shipInfo;
	
	private List<CartLinesInfo> cartLines = new ArrayList<>();

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public ShipInfo getShipInfo() {
		return shipInfo;
	}

	public void setShipInfo(ShipInfo shipInfo) {
		this.shipInfo = shipInfo;
		this.shipInfo.setDistrict(shipInfo.getDistrict());
		this.shipInfo.setWard(shipInfo.getWard());
		this.shipInfo.setAddress(shipInfo.getAddress() +", P"+ this.shipInfo.getWard() +", Q. "+ this.shipInfo.getDistrict());
	}

	public List<CartLinesInfo> getCartLines() {
		return cartLines;
	}
	
	public void addProduct(Product product, int quantity)  {
		if (quantity <= 0 ) {
			return;
		}
		CartLinesInfo cartLineInfo = findCardLineProduct(product.getProductID());

		if (cartLineInfo == null) {
			cartLineInfo = new CartLinesInfo();
			cartLineInfo.setProduct(product);
			cartLineInfo.setQuantity(quantity);
			cartLines.add(cartLineInfo);
		} else {
			int newQuantity = cartLineInfo.getQuantity() + quantity;
			cartLineInfo.setQuantity(newQuantity);
		}
	}
	
	public void updateProduct(int id, int quantity) {
		CartLinesInfo cartLineInfo = findCardLineProduct(id);
		
		if (cartLineInfo != null) {
			cartLineInfo.setQuantity(quantity);
		}
	}
	
	public void removeProduct(Product product) {
		CartLinesInfo cartLineInfo = findCardLineProduct(product.getProductID());
		
		if (cartLineInfo != null) {
			cartLines.remove(cartLineInfo);
		}
	}
	
	public boolean isEmpty() {
		return cartLines.isEmpty();
	}
	
	public int getQuantityTotal() {
		int quantity = 0;
		for(CartLinesInfo line : cartLines) {
			quantity += line.getQuantity();
		}
		return quantity;
	}
	
	public double getAmountTotal() {
		double totalAmount = 0;
		for(CartLinesInfo line : cartLines) {
			totalAmount += line.getAmount();
		}
		return totalAmount;
	}
	
	public void updateQuantity(CartInfo cartForm) {
		if (cartForm != null) {
			List<CartLinesInfo> lines = cartForm.getCartLines();
			for (CartLinesInfo line : lines) {
				if (line.getProduct() != null) {
					this.updateProduct(line.getProduct().getProductID(), line.getQuantity());
				}
			}
		}
	}
	
	public String getTotalPrice(CartInfo cartForm) {
		Integer totalPrice = 0;
		if (cartForm != null) {
			List<CartLinesInfo> lines = cartForm.getCartLines();
			for (CartLinesInfo line : lines) {
				if (line.getProduct() != null) {
					totalPrice+=line.getProduct().getPrice();
				}
			}
		}
		return totalPrice.toString();
	}
	
	
	private CartLinesInfo findCardLineProduct(int id) {
		for (CartLinesInfo line : cartLines) {
			if(line.getProduct().getProductID() == id) {
				return line;
			}
		}
		return null;
	}
	
}
