//package com.ch.config;
//
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Configuration for security.
// * Require admin role for each /admin/ url.
// */
//@Configuration
//@EnableGlobalMethodSecurity( securedEnabled = true )
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//public class SecurityConfig_old extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				.anyRequest().permitAll()
//				.and()
//			.formLogin()
//				.loginPage("/login")
//				.permitAll()
//				.and()
//			.logout()
//				.logoutSuccessUrl("/login?logout")
//				.permitAll();
//	}
//
//
//
//}