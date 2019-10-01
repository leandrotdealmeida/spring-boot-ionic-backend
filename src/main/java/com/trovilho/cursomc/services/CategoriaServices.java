package com.trovilho.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trovilho.cursomc.domain.Categoria;
import com.trovilho.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaServices {

	@Autowired
	CategoriaRepository repository;

	public Categoria findService(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElse(null);
	}

}
