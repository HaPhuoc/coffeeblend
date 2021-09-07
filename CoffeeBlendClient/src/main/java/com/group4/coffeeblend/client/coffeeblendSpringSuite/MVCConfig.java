/**********************************/
/**	Contributor: Luong Duy Hung **/
/** Group:		 4				**/
/**	Class: 		 JDEV-D011      **/
/**********************************/
package com.group4.coffeeblend.client.coffeeblendSpringSuite;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/login").setViewName("login");
		//registry.addViewController("/register").setViewName("register");
	}
}
