package com.practice.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.practice.provider.CustomAuthenticationProvider;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private CustomAuthenticationProvider provider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (provider.supports(authentication.getClass())) {
			return provider.authenticate(authentication);
		}
		throw new BadCredentialsException("key is not matching");
	}

}
