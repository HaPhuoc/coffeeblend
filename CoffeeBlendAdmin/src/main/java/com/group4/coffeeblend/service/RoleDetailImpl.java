package com.group4.coffeeblend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.dao.RoleDetailRepository;
import com.group4.coffeeblend.model.RoleDetails;

@Service
public class RoleDetailImpl {
	@Autowired
	private RoleDetailRepository repo;
	
	public List<RoleDetails> getAll(){
		return repo.findAll();
	}
	public RoleDetails getId(Integer id) {
		return repo.findById(id).get();
	}

	public int removeRole(int id) {
		repo.deleteById(id);
		return id;
	}
	public RoleDetails save(RoleDetails role) {
		return repo.save(role);
	}
}
