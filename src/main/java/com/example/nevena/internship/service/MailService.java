package com.example.nevena.internship.service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.nevena.internship.domain.User;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Async
	public void sendMail(String toWho, String fromWho, String subject, String content) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeHelper = new MimeMessageHelper(message, false, CharEncoding.UTF_8);
		mimeHelper.setFrom(fromWho);
		mimeHelper.setTo(toWho);
		mimeHelper.setSubject(subject);
		mimeHelper.setText(content, true);
		
		javaMailSender.send(message);
		
	}
	

		
	
	public void sendActivationLinkMail(User user) throws MessagingException{
		Context context = new Context();
		context.setVariable("userKey", user);
		
		String content = templateEngine.process("sendActivationLink", context);
		sendMail(user.getEmail(), "majkaca123@gmail.com", "activation link", content);
	}

}


/*@Async
public void sendJobApplicationMail(User user, boolean status) throws MessagingException{
	Context context = new Context();
	context.setVariable("userKey", user);
	
	if(status==true){
		String content = templateEngine.process("acceptedJobApplication", context);
		sendMail(user.getEmail(), "majkaca123@gmail.com", "accepted job application", content);
	}else{
		String content = templateEngine.process("deniedJobApplication", context);
		sendMail(user.getEmail(), "majkaca123@gmail.com", "denied job application", content);

	}
	
	
}
*/