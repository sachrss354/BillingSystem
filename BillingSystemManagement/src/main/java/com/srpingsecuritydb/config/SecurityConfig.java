package com.srpingsecuritydb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	public CustomAuthSuccessHandler successHandler;
	@Autowired
	public AuthenticationFailureHandler failureHandler;
	@Bean
public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
@Bean
public UserDetailsService getDetailsService()
{
return new CustomerUserDetailsService();	
}
@Bean
public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
	daoAuthenticationProvider.setUserDetailsService(getDetailsService());
	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	return daoAuthenticationProvider;
}
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
	
	
	/*http
	.csrf().disable()
	.authorizeHttpRequests().requestMatchers("/","/register","/userlogin","/saveUser").permitAll()
	.requestMatchers("/user/**").authenticated().and()
	.formLogin().loginPage("/userlogin").loginProcessingUrl("/login")
	//.usernameParameter("email")
	.defaultSuccessUrl("/user/profile").permitAll();
	return http.build();*/
	http
	.csrf().disable()
	.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER")
	.requestMatchers("/admin/**").hasRole("ADMIN")
	.requestMatchers("/**").permitAll()
	.and().formLogin().loginPage("/userlogin").loginProcessingUrl("/login")
	.failureHandler(failureHandler)
	.successHandler(successHandler)
	.and().logout().permitAll();
	return http.build();

}
}
