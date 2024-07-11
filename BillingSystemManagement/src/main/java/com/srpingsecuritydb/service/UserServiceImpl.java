package com.srpingsecuritydb.service;

import java.sql.Time;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.srpingsecuritydb.entity.Employee;
import com.srpingsecuritydb.repository.EmpRepo;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private EmpRepo emprepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender mailSender;
	
@Override
public Employee saveEmp(Employee emp,String url) {
	String password=passwordEncoder.encode(emp.getPassword());
	emp.setPassword(password);
	emp.setRole("ROLE_ADMIN");
	emp.setEnable(false);
	emp.setVarificationCode(UUID.randomUUID().toString());
	
	emp.setAccountNonLocked(true);
	emp.setFailedAttempt(0);
	emp.setLockTime(null);
	
	Employee newuser=emprepo.save(emp);
	if(newuser!=null) {
	sendEmail(newuser,url);	
	}
	return newuser;
}
@Override
public void removeSessionmsg() {
		System.out.println("removing message from session");
		HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	    session.removeAttribute("msg");
	    session.setAttribute("msg", null);
}

@Override
public void sendEmail(Employee emp, String url) {
	String from="sachrss354@gmail.com";
	String to=emp.getEmail();
	String subject="Account Verification";
	String content="Dear[[name]],<br>"+"please click the link below to verify your registration:<br>"+
	"<h3><a href=\"[[URL]]\">target=\"_self\">VERIFY</a></h3>"+"Thank You,<br>"+"ExamDrishti";
	try {
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		helper.setFrom(from,"ExamDrishti");
		helper.setTo(to);
		helper.setSubject(subject);
		content=content.replace("[[name]]", emp.getName());
		String siteUrl=url+"/verify?code="+emp.getVarificationCode();
		content=content.replace("[[URL]]", siteUrl);
		helper.setText(content,true);
		mailSender.send(message);
	}catch (Exception e) {
		e.printStackTrace();
	}
	
}
@Override
public boolean verifyAccount(String verificationCode) {
Employee emp=emprepo.findByVarificationCode(verificationCode);
if(emp==null) {
	return false;
}else {
	emp.setEnable(true);
	emp.setVarificationCode(null);
	emprepo.save(emp);
	return true;
}
	
}
@Override
public void increaseFailedAttempt(Employee emp) {
	int attempt=emp.getFailedAttempt()+1;
	emprepo.updateFailedAttempt(attempt, emp.getEmail());
	
}
private static final long lock_duration_time=30000;
public static final long ATTEMPT_TIME=3;
@Override
public void resetAttempt(String email) {
emprepo.updateFailedAttempt(0, email);	

}
@Override
public void lock(Employee emp) {
emp.setAccountNonLocked(false);
emp.setLockTime(new Time(0));
emprepo.save(emp);
	
}

@Override
public boolean unlockAccountTimeExpired(Employee emp) {
	long lockTimeMills=emp.getLockTime().getTime();
	long currentTimeMiils=System.currentTimeMillis();
	if(lockTimeMills+lock_duration_time<currentTimeMiils) {
		emp.setAccountNonLocked(true);
		emp.setLockTime(null);
		emp.setFailedAttempt(0);
		emprepo.save(emp);
		return true;
	}
return false;	
}

}
