/**********************************/
/**	Contributor: Luong Duy Hung **/
/** Group:		 4				**/
/**	Class: 		 JDEV-D011      **/
/**********************************/

package com.group4.coffeeblend.client.coffeeblendSpringSuite.models;



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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name = "customerdetail")
public class AccountDetail {
	/**********************/ 
	/*                    */ 											
	/*       Fields       */
	/*                    */ 											
	/**********************/
	@Id
	@Column(name ="customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "created_time")
	private Date createdTime;
	
	
	
	@Column(name = "last_login")
	private Date lastLogin;
	
	@Size(max = 100, min = 1)
	@NotBlank
	private String name;
	
	@Size(max = 100, min = 5)
	@NotBlank
	private String email;
	
	@Size(min = 1
			
			)
	@NotBlank
	private String password ;
	
	@Size(max = 11, min = 10)
	@NotBlank
	private String phone;
		
	
	@NotBlank
	@Column(name = "address")
	private String address;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "auth_provider")
	private AuthProvider auth_provider;
	
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
		return auth_provider;
	}
	public void setAuthProvider(AuthProvider authProvider) {
		this.auth_provider = authProvider;
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
	public AccountDetail() {
		super();
	}
	
	

	
	
}