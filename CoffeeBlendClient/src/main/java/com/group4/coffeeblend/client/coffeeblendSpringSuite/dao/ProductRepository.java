package com.group4.coffeeblend.client.coffeeblendSpringSuite.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select product from Product product order by price DESC ")
	public List<Product> getTopProducts();
	
	@Query("select product from Product product order by price ASC ")
	public List<Product> getBestSellerProducts();
	
	@Query("select product from Product product where product.type = :type order by price ASC ")
	public List<Product> getProductsByCategory(String type);
	
//	@Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")	
//	public List<Product> search(String keyword);
}
