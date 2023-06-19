package com.practice.config;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import com.practice.filter.CustomAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomAuthenticationFilter customAuthenticationFilter;

	public CustomAuthenticationFilter getCustomAuthenticationFilter() {
		return customAuthenticationFilter;
	}

	public void setCustomAuthenticationFilter(CustomAuthenticationFilter customAuthenticationFilter) {
		this.customAuthenticationFilter = customAuthenticationFilter;
	}

	public SecurityConfig(CustomAuthenticationFilter customAuthenticationFilter) {
		super();
		this.customAuthenticationFilter = customAuthenticationFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

				CorsConfiguration config = new CorsConfiguration();

				config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		}).and().csrf().disable()
				
				.httpBasic().and()
				.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//				.addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests().anyRequest().authenticated().and().build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
