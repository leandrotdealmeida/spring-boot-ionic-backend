package com.trovilho.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.trovilho.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfimationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
