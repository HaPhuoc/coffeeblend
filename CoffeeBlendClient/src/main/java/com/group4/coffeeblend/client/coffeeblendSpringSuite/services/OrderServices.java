package com.group4.coffeeblend.client.coffeeblendSpringSuite.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.CartInfo;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.CartLinesInfo;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Order;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.OrderDetail;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.ShipInfo;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.dao.OrderRepository;
@Service
public class OrderServices {
	@Autowired
	OrderRepository repo;
	
	public void saveNewShipping(CartInfo cartInfo) {
		Date date = new Date();
		
		//long orderNumber = date.getTime();
		
		//cartInfo.setOrderNumber(orderNumber);
		
		ShipInfo shipInfo = cartInfo.getShipInfo();
		
		Order newOrder = new Order();
		
		newOrder.setInvoiceStatus("Đang giao");
		newOrder.setCustomerID(shipInfo.getCustomerId());
		newOrder.setCustomerAddress(shipInfo.getAddress());
		newOrder.setCustomerName(shipInfo.getName());
		newOrder.setCustomerPhone(shipInfo.getPhone());
		newOrder.setTotalSpending(Integer.parseInt(cartInfo.getTotalPrice(cartInfo)));

		newOrder.setDayCreated(date);

		for (CartLinesInfo line : cartInfo.getCartLines() ) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProduct(line.getProduct());
			orderDetail.setPrice(Double.parseDouble(line.getProduct().getPrice().toString()));
			orderDetail.setQuantity(line.getQuantity()); 
			orderDetail.setAmount();
			orderDetail.setOrder(newOrder); //? l;úc
			newOrder.getOrderDetails().add(orderDetail);
		}
		repo.save(newOrder);
	}
}
