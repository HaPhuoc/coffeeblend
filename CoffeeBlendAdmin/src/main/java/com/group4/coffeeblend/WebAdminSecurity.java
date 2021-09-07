package com.group4.coffeeblend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.group4.coffeeblend.service.AccountDetailsImpl;

@Configuration
@EnableWebSecurity
public class WebAdminSecurity extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new AccountDetailsImpl();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authentication = new DaoAuthenticationProvider();
		authentication.setUserDetailsService(userDetailsService());
		authentication.setPasswordEncoder(passwordEncode());
		return authentication;
	}
	@Bean
	 public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		 StrictHttpFirewall firewall = new StrictHttpFirewall();
		 firewall.setAllowUrlEncodedDoubleSlash(true);
		 return firewall;		 
	 }
	
	protected void configure(AuthenticationManagerBuilder auth, WebSecurity web) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/staff-images/**","/product-images/**","/css/**","/fonts/**","/images/**","/jquery-ui-datepicker/**","/js/**","/scss/**","/webfonts/**").hasAnyAuthority("ADMIN","EDITOR")
//		.antMatchers("/","/listProduct","/product-images/*","/edit/*","/editProductType/*","/save/*"
//				, "/delete/*", "/new","/editStaff/*","/addStaff/*").hasAuthority("ADMIN")
		.antMatchers().hasRole("ADMIN")
		.antMatchers().hasAnyRole("EDITOR","ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
		
		
	}
}
