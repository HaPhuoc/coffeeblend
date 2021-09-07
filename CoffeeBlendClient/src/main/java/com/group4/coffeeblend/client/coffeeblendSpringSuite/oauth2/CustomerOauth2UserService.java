/**********************************/
/**	Contributor: Luong Duy Hung **/
/** Group:		 4				**/
/**	Class: 		 JDEV-D011      **/
/**********************************/
package com.group4.coffeeblend.client.coffeeblendSpringSuite.oauth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomerOauth2UserService extends DefaultOAuth2UserService {
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		OAuth2User oauth2User = super.loadUser(userRequest);
		return new CustomerOauth2User(oauth2User);
	}
}	
