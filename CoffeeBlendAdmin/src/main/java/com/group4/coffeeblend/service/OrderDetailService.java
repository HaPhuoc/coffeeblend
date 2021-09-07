package com.group4.coffeeblend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.dao.OrderDetailRepository;
import com.group4.coffeeblend.model.OrderDetail;

@Service
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository repo;
	
	public List<OrderDetail> getAllOrderDetails(){
		return repo.findAll();
	}
	
	public void save(OrderDetail od) {
		repo.save(od);
	}
	
	public OrderDetail get(int id) {
		return repo.findById(id).get();
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
}

