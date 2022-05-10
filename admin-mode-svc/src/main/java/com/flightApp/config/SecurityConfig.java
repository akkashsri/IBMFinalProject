package com.flightApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.log4j.Log4j2;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter{@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		super.configure(http);
		log.info("configure called");
		http.csrf().disable();
	
		http.headers().disable();
		
		http.authorizeHttpRequests().anyRequest().permitAll();
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/actuator/**");
	}
	

}
