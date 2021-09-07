package com.group4.coffeeblend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.dao.ProductStatusRepository;
import com.group4.coffeeblend.model.ProductStatus;

@Service
public class ProductStatusService {
	
	@Autowired
	private ProductStatusRepository repo;
	
	public List<ProductStatus> getAllProductStatus(){
		return repo.findAll();
	}
	
	public void save(ProductStatus status) {
		repo.save(status);
	}
	
	public void delete(ProductStatus status) {
		repo.delete(status);
	}
	
	public ProductStatus get(int status_id) {
		return repo.findById(status_id).get();
	}
}
