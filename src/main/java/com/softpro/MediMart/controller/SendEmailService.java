package com.softpro.MediMart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void SendRegistrationEmail(String mailTo, String name,String pass)
	{
		String subject ="registration succesful welcome to medimart";
		String  message ="hello dear,"+name+"\n your registration is succesful in medimart(MarkLab.)Application\n now you can login to your website through your crendentiles.\n your user ID:"+mailTo+"\nYour password:"+pass+"\n Thank You\n Team MARK LAB. ";
		SimpleMailMessage msg= new SimpleMailMessage();
		msg.setTo(mailTo);
		msg.setSubject(subject);
		msg.setText(message);
		javaMailSender.send(msg);
	}
}
