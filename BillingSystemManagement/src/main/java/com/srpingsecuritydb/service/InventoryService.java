package com.srpingsecuritydb.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.srpingsecuritydb.entity.Inventory;

import jakarta.mail.MessagingException;

public interface InventoryService {
public Inventory saveInventory(Inventory inv);

public List<Inventory> listAll();

public String sendEmail(String to,String subject, String desc,MultipartFile file) throws MessagingException;


}
