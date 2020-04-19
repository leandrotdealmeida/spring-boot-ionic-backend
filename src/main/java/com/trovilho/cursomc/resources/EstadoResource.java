package com.trovilho.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trovilho.cursomc.domain.Cidade;
import com.trovilho.cursomc.domain.Estado;
import com.trovilho.cursomc.dto.CidadeDto;
import com.trovilho.cursomc.dto.EstadoDto;
import com.trovilho.cursomc.services.CidadeService;
import com.trovilho.cursomc.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDto>> findAll(){		
		List<Estado> list = estadoService.findAll();
		List<EstadoDto> listDto = list.stream().map(obj -> new EstadoDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);				
	}
	
	@RequestMapping(value = "/{estado_id}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDto>> findCidades(@PathVariable("estado_id") Integer estadoId){		
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDto> listDto = list.stream().map(obj -> new CidadeDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);				
	}
}