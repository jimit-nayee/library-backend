package com.practice.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.practice.authentication.CustomAuthentication;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Value("${secret.key}")
	private String key;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomAuthentication ca = (CustomAuthentication) authentication;
		String headerKey = ca.getKey();
		
//		System.out.println("provider header key"+ headerKey);
		
		if (key.equals(headerKey)) {
			CustomAuthentication result = new CustomAuthentication(true, null);
			return result;
		}
		throw new BadCredentialsException("key is different");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return CustomAuthentication.class.equals(authentication);
	}

}
