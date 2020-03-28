package com.trovilho.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trovilho.cursomc.domain.Cliente;
import com.trovilho.cursomc.repositories.ClienteRepository;
import com.trovilho.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));

		emailService.sendNewPasswordEmail(cliente, newPass);
		clienteRepository.save(cliente);

	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 0; i++) {
			vet[i] = ramdomChar();
		}
		return new String(vet);

	}

	private char ramdomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) { //gera um digito
			return (char) (rand.nextInt(10) + 48);
		}		
		else if(opt == 1) {  //gera letra minuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { //gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}		
	}
}