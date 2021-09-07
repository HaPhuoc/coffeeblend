package com.group4.coffeeblend.client.coffeeblendSpringSuite;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CoffeeMintPasswordEncryptor {
	public static String EncodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("password in is: " + password);
		return encoder.encode(password);
	}
	
	public static boolean checkPassword(String password, String encryptPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(password, encryptPassword);
	}
}
