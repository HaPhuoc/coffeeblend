package com.group4.coffeeblend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.coffeeblend.model.ProductStatus;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Integer> {

}
