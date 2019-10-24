package com.trovilho.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trovilho.cursomc.domain.Categoria;
import com.trovilho.cursomc.domain.Pedido;
import com.trovilho.cursomc.services.CategoriaServices;
import com.trovilho.cursomc.services.PedidoServices;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	PedidoServices service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Pedido obj = service.findService(id);
		
		return ResponseEntity.ok().body(obj);
	}

}
