package com.srpingsecuritydb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.srpingsecuritydb.entity.Employee;
import com.srpingsecuritydb.repository.EmpRepo;
@Component
public class CustomerUserDetailsService implements UserDetailsService {
@Autowired
	private EmpRepo empRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Employee emp=empRepo.findByEmail(email);
	if(emp==null) {
	throw new UsernameNotFoundException("user nott found"); 
	}
	else {
		return new Customer(emp);
	}
		
	}

}