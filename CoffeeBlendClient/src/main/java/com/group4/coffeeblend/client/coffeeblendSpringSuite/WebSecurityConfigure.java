/**********************************/
/**	Contributor: Luong Duy Hung **/
/** Group:		 4				**/
/**	Class: 		 JDEV-D011      **/
/**********************************/
package com.group4.coffeeblend.client.coffeeblendSpringSuite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.group4.coffeeblend.client.coffeeblendSpringSuite.handlers.OAuthenticationSuccess;
import com.group4.coffeeblend.client.coffeeblendSpringSuite.oauth2.CustomerOauth2UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomerOauth2UserService userService;
	@Autowired
	private OAuthenticationSuccess oauthenticationSuccess;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/", "/css/**", "/fonts/**","/images/**","/jquery-ui-datepicker/**","/js/**",
				"/scss/**","/webfonts/**", "/register","/oauth2/**","/about", "/blog", "/cart" , "/change-password" ,
				"/checkout" , "/contact" , "/info_user","/menu" , "/product-details" , "/register_failed" ,
				"/register_success" , "/services" , "/shop" ,"/header", "/login_with_fault" ).permitAll()
		.anyRequest().permitAll()
		.and()
		.oauth2Login().loginPage("/login").permitAll()
		.userInfoEndpoint().userService(userService)
		.and().successHandler(oauthenticationSuccess)
		.and()
		.logout().permitAll();	
	}
}
