package com.group4.coffeeblend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.dao.ProductRepository;
import com.group4.coffeeblend.helpers.Constant;
import com.group4.coffeeblend.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
//	public List<Product> getAllProduct(String keyword){
//		if(keyword!=null) 
//		{
//			return repo.search(keyword);
//		}
//		return repo.findAll();
//	}
//	public List<Product> getListAll(){
//		return repo.findAll();
//	}
	public Page<Product> getProductPage(int pageNum){
		Pageable pageable = PageRequest.of(pageNum,Constant.PAGE_SIZE);
		return repo.findAll(pageable);
	}
	public Page<Product> getProductPageSort(int pageNum,String sortName,String direction,String keyword){
		Sort sort = Sort.by(sortName);
		sort = direction.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum, Constant.PAGE_SIZE,sort);
		if(keyword != null) {
			return repo.search(keyword, pageable);
		}
		return repo.findAll(pageable);
	}
	public void save(Product product) {
		repo.save(product);
	}
	
	public Product get(int id) {
		return repo.findById(id).get();
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
}
