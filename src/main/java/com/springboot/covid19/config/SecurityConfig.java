package com.springboot.covid19.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
	
	@Autowired
	@Qualifier("securityDataSource")
	private DataSource securityDataSource;
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/users/showFormForAdd").permitAll()
			.antMatchers("/users/save").permitAll()
			.antMatchers("/users/appointmentAdded").permitAll()
			.antMatchers("/users/showFormForUpdate").hasRole("ADMIN")
			.antMatchers("/users/list").hasRole("ADMIN")
			.antMatchers("/users/delete").hasRole("ADMIN")
			.antMatchers("/users/setAppointmentDate").hasRole("ADMIN")
			.antMatchers("/users/clearAppointmentDate").hasRole("ADMIN")
			.antMatchers("/showStaffPage").hasRole("ADMIN")
			.antMatchers("/vaccines/**").hasRole("ADMIN")
			.antMatchers("/resources/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.defaultSuccessUrl("/showStaffPage", true)
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
		
}






