package com.group4.coffeeblend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenarator {
	public static String encoderPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}
