package com.school.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Bean //for safety purpose we are encoding the password
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

    //b-cript is an algorithm for encription and decription  =>spring security package
	@Bean// bean created for filter chain proxy
	SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception{
		return http.csrf(csrf -> csrf.disable()) //1.cross section (It will disable enabling from one application to another application)
				.authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll()//allow every users
			    .anyRequest().authenticated())//2.accepting all end points and permit all user
				.formLogin(Customizer.withDefaults())//3.customising longin form
				.build();
	}
	@Bean
	AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;

	}
}
