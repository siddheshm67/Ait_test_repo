package com.management.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMail(String subject,String body,String email) {
		
		boolean mailSent = false;
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			//some chnages here also
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setTo(email);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body,true);
			javaMailSender.send(mimeMessage);
			mailSent = true;
		} catch (Exception e) {
			e.getMessage();
		}
		return mailSent;
	}

}
