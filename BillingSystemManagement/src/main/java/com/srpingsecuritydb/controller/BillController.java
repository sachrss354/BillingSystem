package com.srpingsecuritydb.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.srpingsecuritydb.entity.Inventory;
import com.srpingsecuritydb.repository.InventoryRepo;
import com.srpingsecuritydb.service.InventoryService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class BillController {
	@Autowired
	private InventoryService invservice;
	@Autowired
	private InventoryRepo  invRepo;
	@GetMapping("/bill")
public String bill() {
	return "bill";
}
	@GetMapping("/additem")
	public String additem(){
		return "additem";
	}
	@PostMapping("/saveProduct")
	public String saveInventory(Inventory inv) {
		invservice.saveInventory(inv);
		return "redirect:/additem";
	}
	@GetMapping("/rate")
	public String rate() {
		return "rate";
	}
	@GetMapping("/api/product/mrp")
    public ResponseEntity<?> getProductMrp(@RequestParam String name) {
		System.out.println(name);
		  Inventory inventory = invRepo.findByName(name);
	        if (inventory != null) {
	        	System.out.println(inventory.getMrp());
	            return ResponseEntity.ok(inventory.getMrp());
	        }  
       return new ResponseEntity<String>("No dataFound",HttpStatus.INTERNAL_SERVER_ERROR);
    }
	@GetMapping("/contact")
public String sendEmail() {
		return "sendEmail";
	}
	@PostMapping("/sendEmail")
	public String sendMail(@RequestParam("file") MultipartFile file, String to, String subject, String desc, HttpSession session) throws MessagingException {
	    if (!file.isEmpty()) {
	        try {
	           String msg=invservice.sendEmail(to, subject, desc, file);
	            session.setAttribute("msg",msg);
	        } catch (Exception e) {
	            session.setAttribute("msg", "Failed to send email: " + e.getMessage());
	        }
	    } else {
	    	 String msg1=invservice.sendEmail(to, subject, desc, file);
	        session.setAttribute("msg", msg1);
	    }
	    return "redirect:/contact";
}
	@GetMapping("/chart")
	public String chart() {
		return "chart";
	}
}

