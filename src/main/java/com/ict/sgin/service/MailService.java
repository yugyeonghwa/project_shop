package com.ict.sgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired 
	private JavaMailSender mailSender;
	
	// 비밀번호 찾기 메일
	public void pwdEmail(String randomNumber, String toMail) {
		try {
			MailHandler sendMail = new MailHandler(mailSender);
			
			// 메일 제목
			sendMail.setSubject("[SHOP 임시 비밀번호 발급 메일입니다.]");
			
			// 메일 내용
			sendMail.setText("" 
		            + "<p>안녕하세요.</p><br>"
		            + "<p>임시 비밀번호가 발급되었습니다.</p><br>"
		            + "<p>발급받은 임시 비밀번호로 로그인 후 비밀번호를 변경하십시오.</p><br>"
		            + "<p>임시 비밀번호 : "+randomNumber +"</p>");
			
			// 보내는 사람
			sendMail.setFrom("shop@gmail.com", "SHOP");
			
			// 받는 사람
			sendMail.setTo(toMail);
			sendMail.send();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	// 아이디 찾기 메일
	public void idEmail(String randomNumber, String toMail) {
		try {
			MailHandler sendMail = new MailHandler(mailSender);
			
			// 메일 제목
			sendMail.setSubject("[SHOP 인증번호 발급 메일입니다.]");
			
			// 메일 내용
			sendMail.setText("" 
					+ "<p>안녕하세요.</p><br>"
					+ "<p>아이디 찾기 인증번호가 발급되었습니다.</p><br>"
					+ "<p>인증번호 : "+randomNumber +"</p>");
			
			// 보내는 사람
			sendMail.setFrom("shop@gmail.com", "SHOP");
			
			// 받는 사람
			sendMail.setTo(toMail);
			sendMail.send();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
