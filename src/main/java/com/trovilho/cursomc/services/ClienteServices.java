package com.trovilho.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trovilho.cursomc.domain.Cliente;
import com.trovilho.cursomc.repositories.ClienteRepository;
import com.trovilho.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteServices {

	@Autowired
	ClienteRepository repository;

	public Cliente findService(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +", Tipo: " + 
		Cliente.class.getName()));
	}

}
