package com.srpingsecuritydb.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.srpingsecuritydb.entity.Employee;
import com.srpingsecuritydb.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
private UserService service;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Set<String> roles=AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		Customer customUser=(Customer)authentication.getPrincipal();
		Employee emp=customUser.getEmp();
		if(emp!=null) {
			service.resetAttempt(emp.getEmail());
		}
		if(roles.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/profile");
		}
		else {
			response.sendRedirect("/user/profile");
		}
	}

}
