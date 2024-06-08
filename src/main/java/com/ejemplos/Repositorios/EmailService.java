package com.ejemplos.Repositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;

	
	public void enviarEmail(String to, String subject, String text) {
		SimpleMailMessage mensaje=new SimpleMailMessage();
		
		mensaje.setTo(to);
		mensaje.setSubject(subject);
		mensaje.setText(text);
		mensaje.setFrom("ibvelarde@gmail.com");//este es el remitente para q le aparezca al destinatario
		mailSender.send(mensaje);
		
	}
}
