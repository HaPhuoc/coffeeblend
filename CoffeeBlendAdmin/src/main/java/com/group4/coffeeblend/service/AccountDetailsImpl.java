package com.group4.coffeeblend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.group4.coffeeblend.MyAdminDetails;
import com.group4.coffeeblend.PasswordGenarator;
import com.group4.coffeeblend.dao.AccountRepository;
import com.group4.coffeeblend.model.AccountDetails;

@Service
public class AccountDetailsImpl implements UserDetailsService {

	@Autowired
	private AccountRepository accountRsp;
	
	@Override
	public UserDetails loadUserByUsername(String emailAdmin) throws UsernameNotFoundException {
		
		AccountDetails acc = accountRsp.findbyEmail(emailAdmin);
		if(acc == null) {
			throw new UsernameNotFoundException("Khong tim thay!!!!");
		}
		return new MyAdminDetails(acc);
	}
	
	public void save(AccountDetails account) {
		PasswordGenarator passEncoder = new PasswordGenarator();
		Date date = new Date();
		account.setCreateTime(date);
		account.setPassword(passEncoder.encoderPassword(account.getPassword()));
		accountRsp.save(account);
	}
	public List<AccountDetails> getList(){
		return accountRsp.findAll();
	}
	public void delete(Integer id) {
		accountRsp.deleteById(id);
	}
	
	public AccountDetails getId(Integer id) {
		return accountRsp.findById(id).get();
	}
	
	
}
