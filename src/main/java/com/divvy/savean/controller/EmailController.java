package com.divvy.savean.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.divvy.savean.email.EmailConfig;
import com.divvy.savean.model.Email;


@RestController
public class EmailController
{
	EmailConfig emailConfig;

	public EmailController(EmailConfig emailConfig) {
		super();
		this.emailConfig = emailConfig;
	}
	
	@PostMapping("/email")
	public void sendMail(@RequestBody Email email)
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(emailConfig.getHost());
		mailSender.setPort(emailConfig.getPort());
		mailSender.setUsername(emailConfig.getUsername());
		mailSender.setPassword(emailConfig.getPassword());
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getMsgTo());
		mailMessage.setSubject(email.getSubject());
		mailMessage.setText(email.getMessage());
		mailMessage.setFrom("dummy@gmail.com");
		
		mailSender.send(mailMessage);
	}
}
