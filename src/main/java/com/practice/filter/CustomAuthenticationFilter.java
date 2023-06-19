package com.practice.filter;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.practice.authentication.CustomAuthentication;
import com.practice.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomAuthenticationManager customAuthenticationManager;

	public CustomAuthenticationFilter(CustomAuthenticationManager customAuthenticationManager) {
		super();
		this.customAuthenticationManager = customAuthenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String key = String.valueOf(request.getHeader("key"));
		CustomAuthentication ca = new CustomAuthentication(false, key);

		
		var a = customAuthenticationManager.authenticate(ca);
		if (a.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(a);
			filterChain.doFilter(request, response);
		}
	}
}
