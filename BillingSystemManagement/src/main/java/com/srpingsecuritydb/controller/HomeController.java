package com.srpingsecuritydb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srpingsecuritydb.entity.Employee;
import com.srpingsecuritydb.entity.Inventory;
import com.srpingsecuritydb.repository.EmpRepo;
import com.srpingsecuritydb.service.InventoryService;
import com.srpingsecuritydb.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	@SuppressWarnings("unused")
	@Autowired
	private EmpRepo emprepo;
	@Autowired
	private InventoryService invservice;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	@GetMapping("/")
public String index() {
	return "index";
}
/*
	@ModelAttribute
public String profile(Principal p,Model m) {
		String email=p.getName();
		Employee empo=emprepo.findByEmail(email);
		m.addAttribute("user",empo);
	return "profile";
}*/
@GetMapping("/userlogin")
public String signin(Inventory inv,HttpSession session) {
		List<Inventory> invt=invservice.listAll();
		session.setAttribute("listinv", invt);
	return "login";
}
	@GetMapping("/invalid")
public String invalid() {
	return "error";
}
	@GetMapping("/userlogout")
public String logout(HttpSession sess) {
		//sess.setAttribute("msg", "Log out SuccesFully!!");
	return "logout";
}
	@GetMapping("/register")
public String register() {
	return "register";
}
	@GetMapping("home")
public String home() {
	return "home";
}
	@PostMapping("/saveUser")
	public String saveEmp(@ModelAttribute Employee emp,HttpSession session,Model m,HttpServletRequest request) {
		String url=request.getRequestURL().toString();
	     url=url.replace(request.getServletPath(),"");
	     Employee e=userService.saveEmp(emp,url);
	      if((e.getPassword()!=null)&&(e.getEmail()!=null)) {
	    	System.out.println("Save Success!!");  
	    	session.setAttribute("msg","Registration Successfully done!!Verification link has been send your Email");
	      }
	      else{
	    	  System.out.println("Save Failed!!"); 
	    	  session.setAttribute("msg", "Registration Failed!!");
	    	  
	      }
	      return "redirect:/register";
	}
	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code,Model m) {
	boolean b=userService.verifyAccount(code);
	if(b) {
		m.addAttribute("msg","Your Account is Verified!!");
	}	else {
		m.addAttribute("msg","may be your verification code is incorrect or already done!!");
	}
	return "message";
	}
	@GetMapping("/offer")
	public String offer() {
		return "offer";
	}
	@GetMapping("/forgot_password")
	public String forgot_password(HttpSession  sess) {
		sess.invalidate();
		return "forgot_password";
	}
	@GetMapping("/reset_password/{id}")
	public String reset_password(@PathVariable int id,Model m) {
		System.out.println("id::"+id);
		m.addAttribute("id",id);
		return "reset_password";
	}
	@GetMapping("/load_forgot_password")
	public String load_forgot_password(@RequestParam String email,@RequestParam String mobile,HttpSession session) {
		Employee emp=emprepo.findByEmailAndMobile(email, mobile);
		if(emp!=null) {
			return "redirect:/reset_password/"+emp.getId();
			}
		else {
			session.setAttribute("msg","Ivalid Email&Mobile Please Enter Correct Email And Password");
			return "forgot_password";	
		}
	}
	@PostMapping("/changePassword")
	public String resetPassword(@RequestParam String psw,@RequestParam Integer id,HttpSession session) {
		
		Employee emp=emprepo.findById(id).get();
		String encrypt=passEncoder.encode(psw);
		System.out.println(psw);
		emp.setPassword(encrypt);
		Employee update=emprepo.save(emp);
		if(update!=null)
		{
			session.setAttribute("msg","Password has been changed successfully!!");
		}
		return "redirect:/forgot_password";
	}
}
