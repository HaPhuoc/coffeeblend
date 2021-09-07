package com.group4.coffeeblend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.dao.ProductTypeRepository;
import com.group4.coffeeblend.model.ProductType;

@Service
public class ProductTypeService {

	@Autowired
	private ProductTypeRepository repo;
	
	public List<ProductType> getAllProductTypes(){
		return repo.findAll();
	}
	
	public void save(ProductType type) {
		repo.save(type);
	}
	
	public ProductType get(int type_id) {
		return repo.findById(type_id).get();
	}
	
	public void delete(int type_id) {
		repo.deleteById(type_id);
	}
}
