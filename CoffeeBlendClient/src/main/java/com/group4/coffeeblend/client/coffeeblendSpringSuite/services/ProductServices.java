package com.group4.coffeeblend.client.coffeeblendSpringSuite.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.dao.ProductRepository;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Product;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers.Constant;


@Service
public class ProductServices {
	@Autowired
	private ProductRepository repo;
//	public Product search(String toSearch) {
//		
//	}
	public List<Product> getAllProduct() {
		return repo.findAll();
	}
	
	public List<Product> getTopProducts(int top) {
		List<Product> topProduct = repo.getTopProducts();
		List<Product> topProducts = new ArrayList<>();
		for(int  i = 0 ;i <top ; i++) {
			Product temp = topProduct.get(i);
			topProducts.add(temp);
		}
		 return topProducts;
	}
	
	public List<Product> getProductByCategory(String type, int top){
		List<Product> categoryProduct = repo.getProductsByCategory(type);
		List<Product> categoryProducts = new ArrayList<>();
		for(int  i = 0 ;i <top ; i++) {
			Product temp = categoryProduct.get(i);
			categoryProducts.add(temp);
		}
		 return categoryProducts;
	}
	
	public List<Product> getBestSellerProducts() {
		List<Product> topProduct = repo.getBestSellerProducts();
		List<Product> top5Products = new ArrayList<>();
		for(int  i = 0 ;i <5 ; i++) {
			Product temp = topProduct.get(i);
			top5Products.add(temp);
		}
		 return top5Products;
	}
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public Product get(Integer id) {
		return repo.findById(id).get();
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}

//	public List<Product> find(String key) {
//		return repo.search(key);
//		
//	}

	
}
