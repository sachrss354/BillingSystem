package com.srpingsecuritydb.entity;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
private int id;
private String name;
private String mobile;
private String role;
private String email;
private String password;
private boolean enable;
private String varificationCode;
private boolean isAccountNonLocked;
private int failedAttempt;
private Time lockTime;
private int cid;

public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public boolean isAccountNonLocked() {
	return isAccountNonLocked;
}
public void setAccountNonLocked(boolean isAccountNonLocked) {
	this.isAccountNonLocked = isAccountNonLocked;
}
public int getFailedAttempt() {
	return failedAttempt;
}
public void setFailedAttempt(int failedAttempt) {
	this.failedAttempt = failedAttempt;
}

public Time getLockTime() {
	return lockTime;
}
public void setLockTime(Time lockTime) {
	this.lockTime = lockTime;
}
public boolean isEnable() {
	return enable;
}
public void setEnable(boolean enable) {
	this.enable = enable;
}
public String getVarificationCode() {
	return varificationCode;
}
public void setVarificationCode(String varificationCode) {
	this.varificationCode = varificationCode;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

}
