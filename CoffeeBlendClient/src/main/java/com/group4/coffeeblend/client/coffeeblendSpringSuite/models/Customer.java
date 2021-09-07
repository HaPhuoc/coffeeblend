/**********************************/
/**	Contributor: Luong Duy Hung **/
/** Group:		 4				**/
/**	Class: 		 JDEV-D011      **/
/**********************************/

package com.group4.coffeeblend.client.coffeeblendSpringSuite.models;


import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers.AuthProvider;

@Entity
@Table(name = "accountdetail")
public class Customer {
	/**********************/ 
	/*                    */ 											
	/*       Fields       */
	/*                    */ 											
	/**********************/
	@Id
	@Column(name ="AccountID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "createdTime")
	private Date createdTime;
	
	@Column(name = "RoleID")
	private Integer roleId;
	
	@Column(name = "lastLogin")
	private Date lastLogin;

	private String name;
	private String email;
	private String password ;
	private String phone;
	
	@Column(name = "Address")
	private String address;
	
	@Enumerated(EnumType.STRING)
	private AuthProvider authProvider;
	private Blob img;
	private Boolean enabled;
	
	/***********************/ 
	/*                    */ 											
	/* Getters and Setters*/
	/*                    */ 											
	/**********************/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public AuthProvider getAuthProvider() {
		return authProvider;
	}
	public void setAuthProvider(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}
	public Blob getImg() {
		return img;
	}
	public void setImg(Blob img) {
		this.img = img;
	}
	public Boolean getIsEnabled() {
		return enabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.enabled = isEnabled;
	}
	/***********************/ 
	/*                    */ 											
	/*     Constructor    */
	/*                    */ 											
	/**********************/
	public Customer(Boolean isEnabled) {
		super();
		this.enabled = isEnabled;
	}
	
	

	
	
}