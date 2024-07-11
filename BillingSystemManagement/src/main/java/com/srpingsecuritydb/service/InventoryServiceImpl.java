package com.srpingsecuritydb.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.srpingsecuritydb.entity.Inventory;
import com.srpingsecuritydb.repository.InventoryRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class InventoryServiceImpl implements InventoryService {
@Autowired
private InventoryRepo invrepo;
@Autowired
private JavaMailSender sender;
@Override
public Inventory saveInventory(Inventory inventory) {
	return invrepo.save(inventory);
}
@Override
public List<Inventory> listAll(){
	return invrepo.findAll();
}
	
@Override
public String sendEmail(String to,String subject, String desc,MultipartFile file) throws MessagingException {
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(desc);
   if(!file.isEmpty()) {
    helper.addAttachment(file.getOriginalFilename(), file);
    sender.send(message);
    return "Meassage and File has been sent on your email";
    }
   else {
	   sender.send(message);
	    return "Meassage and File has been sent on your email";
   }
    
}
}

