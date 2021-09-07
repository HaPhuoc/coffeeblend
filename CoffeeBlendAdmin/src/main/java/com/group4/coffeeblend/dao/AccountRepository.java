package com.group4.coffeeblend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.group4.coffeeblend.model.AccountDetails;
import com.group4.coffeeblend.model.RoleDetails;


public interface AccountRepository extends JpaRepository<AccountDetails, Integer> {
	
	@Query("Select acc from AccountDetails acc where acc.email = :email")
	public AccountDetails findbyEmail(@Param("email")String email);
	
//	@Query("Select rle from RoleDetails rle where rle.name = :name")
//	public RoleDetails getRolebyName(@Param("name") String name);
}
