package com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers;

import javax.servlet.http.HttpServletRequest;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.AccountDetail;

public class UserLogInUtil {
	private static final String MYAD = "MyAccountDetail";
	
	public static AccountDetail getUserInSession(HttpServletRequest request) {
		return (AccountDetail)request.getSession().getAttribute(MYAD);
	}
	
	public static void setAccountDetail(HttpServletRequest request, AccountDetail ad) {
		if( ad != null) {
			request.getSession().setAttribute(MYAD, ad);
		}
	} 
	public static void removeUserInSession(HttpServletRequest request) {
		request.getSession().removeAttribute(MYAD);
	}
	
	
}
