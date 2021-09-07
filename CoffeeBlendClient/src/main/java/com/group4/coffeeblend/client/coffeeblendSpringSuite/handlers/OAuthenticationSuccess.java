/**********************************/
/**	Contributor: Luong Duy Hung **/
/** Group:		 4				**/
/**	Class: 		 JDEV-D011      **/
/**********************************/
package com.group4.coffeeblend.client.coffeeblendSpringSuite.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers.AuthProvider;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.AccountDetail;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.oauth2.CustomerOauth2User;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.services.CustomerServices;
@Component
public class OAuthenticationSuccess extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private CustomerServices customerServices;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		CustomerOauth2User oauth2User  = (CustomerOauth2User) authentication.getPrincipal();
		String email = oauth2User.getEmail();
		String fullname = oauth2User.getName();
		String servletPath = request.getServletPath();
		
		
		System.out.println("Customer logged in by email: "+ email);
		System.out.println("Customer logged in by name: "+ fullname);
		System.out.println("Customer logged server path: "+ servletPath);
		
		AuthProvider provider = AuthProvider.BASIC;
		if(servletPath.contains("facebook")) provider = AuthProvider.FACEBOOK;
		else if(servletPath.contains("google")) provider = AuthProvider.GOOGLE;
		else provider = AuthProvider.BASIC;
		
		AccountDetail customer = customerServices.getByEmail(email);
		if(customer == null) customerServices.registerNewCustomer(email, fullname, provider);
		else customerServices.updateCustomer(customer, fullname, provider);
		super.onAuthenticationSuccess(request, response, authentication);
		
	}
}
