package com.group4.coffeeblend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.dao.InvoiceRepository;
import com.group4.coffeeblend.model.Invoice;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository repo;
	
	public List<Invoice> getAllInvoice(){
		return repo.findAll();
	}
	
	public void save(Invoice invoice) {
		repo.save(invoice);
	}
	
	public Invoice get(int id) {
		return repo.findById(id).get();
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}

}
