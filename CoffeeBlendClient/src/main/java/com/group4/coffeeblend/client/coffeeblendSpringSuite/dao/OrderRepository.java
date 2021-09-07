package com.group4.coffeeblend.client.coffeeblendSpringSuite.dao;

import org.springframework.data.repository.CrudRepository;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
	
}
