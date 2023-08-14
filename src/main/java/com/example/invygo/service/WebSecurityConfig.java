/*
package com.example.invygo.service;

import com.example.invygo.entity.Role;
import com.example.invygo.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/api/v1/schedule/add").hasAuthority("ADMIN")
			.antMatchers("/api/v1/schedule/delete").hasAuthority("ADMIN")
			.antMatchers("/api/v1/user/fetchAll").hasAnyAuthority("ADMIN", "STAFF")
			.antMatchers("/api/v1/schedule/fetchAll").hasAnyAuthority("ADMIN", "STAFF")
			.anyRequest().authenticated()
			.and().csrf().disable();
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inMemoryUserDetailsManager());
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		final User user = new User();
		user.setUsername("abc");
		user.setPassword("abc");
		user.setEnabled(true);
		Role role = new Role();
		role.setName("ADMIN");
		Set<Role> set= new HashSet<>();
		set.add(role);
		user.setRoles(set);
		return new InMemoryUserDetailsManager(new MyUserDetails(user));
	}
}
*/
