package com.group4.coffeeblend.client.coffeeblendSpringSuite.helpers;

import javax.servlet.http.HttpServletRequest;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.models.CartInfo;

public class ShopCartUtil {

	private static final String MYCART = "MyCart";
	private static final String LASTCART = "LastCart";
	
public static CartInfo getCartInSession(HttpServletRequest request) {
		
		CartInfo cartInfo = (CartInfo) request.getSession().getAttribute(MYCART);
		
		if (cartInfo == null) {
			cartInfo = new CartInfo();
			request.getSession().setAttribute(MYCART, cartInfo);
		}
		
		return cartInfo;
	}
	

public static void removeCartInSession(HttpServletRequest request) {
	request.getSession().removeAttribute(MYCART);
}

public static void saveLastCartInSession(HttpServletRequest request, 
		CartInfo cartInfo) {
	request.getSession().setAttribute(LASTCART, cartInfo);
	
}

public static CartInfo loadLastCartInSession(HttpServletRequest request) {
	return (CartInfo)request.getSession().getAttribute(LASTCART);
	
}
}
