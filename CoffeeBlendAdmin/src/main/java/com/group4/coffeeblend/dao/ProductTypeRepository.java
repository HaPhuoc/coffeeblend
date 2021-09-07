package com.group4.coffeeblend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.coffeeblend.model.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
	
	

}
