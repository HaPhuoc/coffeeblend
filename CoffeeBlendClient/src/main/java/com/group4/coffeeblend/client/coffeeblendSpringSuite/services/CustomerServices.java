/**********************************/
/**	Contributor: Luong Duy Hung **/
/** Group:		 4				**/
/**	Class: 		 JDEV-D011      **/
/**********************************/
package com.group4.coffeeblend.client.coffeeblendSpringSuite.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers.AuthProvider;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.CoffeeMintPasswordEncryptor;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.dao.CustomerRepository;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.AccountDetail;
//import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.Customer;

@Service
public class CustomerServices {
	@Autowired 
	private CustomerRepository repo;
	
	public List<AccountDetail> listAllCustomer(){
		return repo.findAll();
	}
	
	public AccountDetail getById(){
		List<AccountDetail> customerL = repo.getById();
		AccountDetail lastLine = customerL.get(0);
		return  lastLine;
	}
	
	public AccountDetail getByEmail(String email) {
		return repo.getByEmail(email);
	}
	
	//public AccountDetail getById(Integer id) {
	//	return repo.getById(id);
	//}
	
	public void registerNewCustomer(String email, String fullname, AuthProvider provider) {
		AccountDetail customer = new AccountDetail();
		Date date = new Date();
		customer.setLastLogin(date);
		customer.setEmail(email);
		customer.setName(fullname);
		customer.setAuthProvider(provider);
		repo.save(customer);
	}	
	public void updateCustomer(AccountDetail customer, String fullname, AuthProvider provider) {
		customer.setName(fullname);
		customer.setAuthProvider(provider);
		Date date = new Date();
		customer.setLastLogin(date);
		repo.save(customer);
	}

	public void registerNewCustomer(AccountDetail customer, AuthProvider basic) {
		// TODO Auto-generated method stub
		Date date = new Date();
		customer.setLastLogin(date);
		customer.setCreatedTime(date);
		customer.setAuthProvider(basic);
		//CoffeeMintPasswordEncryptor encoder = new CoffeeMintPasswordEncryptor();
		String encryptPassword =CoffeeMintPasswordEncryptor.EncodePassword(customer.getPassword());
		customer.setPassword(encryptPassword);
		repo.save(customer);
	}
	
	
}
