package com.trovilho.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.trovilho.cursomc.domain.Cliente;
import com.trovilho.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfimationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido pedido);
	
	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);

}