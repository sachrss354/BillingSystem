package com.srpingsecuritydb.service;

import java.beans.JavaBean;

import com.srpingsecuritydb.entity.Employee;
@JavaBean
public interface UserService {
	
public Employee saveEmp(Employee emp,String url);

public void removeSessionmsg();

public void sendEmail(Employee emp,String path);

public boolean verifyAccount(String verificationCode);

public void increaseFailedAttempt(Employee emp);

public void resetAttempt(String email);

public void lock(Employee emp);

public boolean unlockAccountTimeExpired(Employee emp);
}