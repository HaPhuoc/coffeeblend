/**********************************/
/**	Contributor: Luong Duy Hung **/
/** Group:		 4				**/
/**	Class: 		 JDEV-D011      **/
/**********************************/
package com.group4.coffeeblend.client.coffeeblendSpringSuite.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.AccountDetail;
//import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Customer;

public interface CustomerRepository extends JpaRepository<AccountDetail, Integer> {
	@Query(value = "Select ad from AccountDetail ad where ad.email = :email")
	public AccountDetail getByEmail(String email);
	//@Query(value = "Select id from AccountDetail ad where ad.email = :email")

	
	@Query(value = "Select ad from AccountDetail ad order by id DESC")
	public List<AccountDetail> getById();
	//public AccountDetail getById(Integer id);
}
