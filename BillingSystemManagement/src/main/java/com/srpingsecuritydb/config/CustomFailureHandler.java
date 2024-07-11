package com.srpingsecuritydb.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.srpingsecuritydb.entity.Employee;
import com.srpingsecuritydb.repository.EmpRepo;
import com.srpingsecuritydb.service.UserService;
import com.srpingsecuritydb.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler{
@Autowired
	private UserService service;
@Autowired
private EmpRepo erepo;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {		
		String email=request.getParameter("username");		
		Employee emp=erepo.findByEmail(email);	
		if(emp!=null) {
			if(emp.isEnable()) {
			if(emp.isAccountNonLocked()) {
				if(emp.getFailedAttempt()<UserServiceImpl.ATTEMPT_TIME-1) {
					service.increaseFailedAttempt(emp);
				}else {
					service.lock(emp);
					exception=new LockedException("Your Acount is locked!!failed attempt 3 times");
				}
			}else if(!emp.isAccountNonLocked()) {
			    if(service.unlockAccountTimeExpired(emp)) {
			    	exception=new LockedException("Account is unlocked!please try to login");
			    }else {
			    	exception =new LockedException("Account is Locked!Please Try after some time");
			    }
			}
			}else {
				exception=new LockedException("Account is inactive..verify account");
			}
		}
		super.setDefaultFailureUrl("/userlogin?error");
		super.onAuthenticationFailure(request, response, exception);
	}

}
