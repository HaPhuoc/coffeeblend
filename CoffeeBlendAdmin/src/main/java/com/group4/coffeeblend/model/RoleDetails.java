package com.group4.coffeeblend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roledetail")
public class RoleDetails {
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "role_name")
	private String name;
	
	public RoleDetails() {
		
	}
	public RoleDetails(String name) {
		super();
		this.name = name;
	}
	public RoleDetails(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public RoleDetails(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
