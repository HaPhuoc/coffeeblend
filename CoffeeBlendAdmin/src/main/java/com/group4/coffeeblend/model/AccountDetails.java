package com.group4.coffeeblend.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.group4.coffeeblend.helpers.Constant;
import com.sun.istack.NotNull;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
@Table(name = "admindetail")
public class AccountDetails {
	@Id
	@Column(name = "admin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "admin_fullname")
	private String fullName;
	
	@Column(name = "admin_email",length = 45)
	private String email;
	
	@Column(name = "admin_user")
	private String adminUser;
	
	@Column(name = "admin_address")
	private String address;
	
	@Column(name = "admin_pass",length = 64)
	private String password;
	
	@Column(name = "admin_phone")
	private String numberPhone;
	
	@Column(name = "admin_image")
	private String picture;
	
	@Column(name = "admin_createdtime")
	private Date createTime;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "admin_role")
	private Integer role_id;
	
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userrole", joinColumns = @JoinColumn(name = "admin_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleDetails> role = new HashSet<>();

	public Set<RoleDetails> getRole() {
		return role;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdminUser() {
		return adminUser;
	}
	
	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public void setRole(Set<RoleDetails> role) {
		this.role = role;
	}
	
	@Transient
	public String getFullPath() {
//		System.out.println("get full path: " + picture);
		
		if (picture == null || id == null)
			return null; // neu ko co file nao het thi return null
		String path = "/" + Constant.UPLOAD_IMAGE + "/" + id + "/" + picture;
		System.out.println("Product image full path: " + path);
		return path;
	}

}
