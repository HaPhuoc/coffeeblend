package com.group4.coffeeblend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.group4.coffeeblend.model.AccountDetails;
import com.group4.coffeeblend.model.RoleDetails;

public class MyAdminDetails implements UserDetails {

	@Autowired
	private AccountDetails accDetails;
	@Autowired
	private RoleDetails role;
	
	public MyAdminDetails(AccountDetails acc) {
		// TODO Auto-generated constructor stub
		super();
		this.accDetails = acc;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		Set<RoleDetails> role = accDetails.getRole();
		System.out.println("authorities Email: "+accDetails.getEmail());
		for (RoleDetails roleDetails : role) {
			authorities.add(new SimpleGrantedAuthority(roleDetails.getName()));
			System.out.println("authorities host name: "+roleDetails.getName());
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return accDetails.getPassword();
	}

	@Override
	public String getUsername() {
		
		return accDetails.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
