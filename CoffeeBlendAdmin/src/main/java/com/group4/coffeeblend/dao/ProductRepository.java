package com.group4.coffeeblend.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.group4.coffeeblend.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

//	@Query("SELECT p FROM Product p WHERE p.product_name LIKE %?1%"
//            + " OR p.type LIKE %?1%"
//            + " OR p.price LIKE %?1%"
//            + " OR CONCAT(p.status, '') LIKE %?1%")
	@Query("SELECT p FROM Product p WHERE CONCAT(p.product_name,'', p.type,'', p.price,'',p.status) LIKE %?1% ")
//	@Query("SELECT p FROM Product p WHERE CONCAT(p.product_name) LIKE %?1% ")
	public Page<Product> search(String keyword,Pageable pageable);
	
}
