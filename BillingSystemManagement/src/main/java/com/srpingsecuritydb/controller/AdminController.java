package com.srpingsecuritydb.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srpingsecuritydb.entity.Employee;
import com.srpingsecuritydb.repository.EmpRepo;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private EmpRepo erepo;
	@ModelAttribute
	public void commonUser(Principal p,Model m,HttpSession ses) {
		if(p!=null) {
			String email=p.getName();
			Employee empo=erepo.findByEmail(email);
			ses.setAttribute("user1", empo);
			m.addAttribute("user",empo);
		}
	}
	@GetMapping("/profile")
public String adminprofile() {
	return "admin_profile";
}
}
