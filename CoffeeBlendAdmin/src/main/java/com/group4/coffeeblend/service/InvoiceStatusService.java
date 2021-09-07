package com.group4.coffeeblend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.dao.InvoiceStatusRepository;
import com.group4.coffeeblend.model.InvoiceStatus;

@Service
public class InvoiceStatusService {

	@Autowired
	private InvoiceStatusRepository repo;
	
	public List<InvoiceStatus> getAllInvoiceStatus(){
		return repo.findAll();
	}
	
	public void save(InvoiceStatus invoiceStatus) {
		repo.save(invoiceStatus);
	}
	
	public InvoiceStatus get(int id) {
		return repo.findById(id).get();
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
}
