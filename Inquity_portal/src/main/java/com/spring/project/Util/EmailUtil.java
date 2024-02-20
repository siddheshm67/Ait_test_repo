package com.spring.project.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

import jakarta.mail.internet.MimeMessage;

@Controller
public class EmailUtil {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMail(String subject,String body,String to) {
		boolean mailSent = false;
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setTo(to);
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
