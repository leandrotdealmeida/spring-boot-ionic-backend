package com.trovilho.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.function.ObjDoubleConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trovilho.cursomc.domain.Cidade;
import com.trovilho.cursomc.domain.Cliente;
import com.trovilho.cursomc.domain.Endereco;
import com.trovilho.cursomc.domain.enums.TipoCliente;
import com.trovilho.cursomc.dto.ClienteDto;
import com.trovilho.cursomc.dto.ClienteNewDto;
import com.trovilho.cursomc.repositories.CidadeRepository;
import com.trovilho.cursomc.repositories.ClienteRepository;
import com.trovilho.cursomc.repositories.EnderecoRepository;
import com.trovilho.cursomc.services.exceptions.DateIntegrationException;
import com.trovilho.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteServices {

	@Autowired
	ClienteRepository repository;

	@Autowired
	CidadeRepository repositoryCidade;

	@Autowired
	EnderecoRepository repositoryEndereco;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		repositoryEndereco.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);

		try {
			repository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DateIntegrationException("Não é possivel excluir porque há entidades relacionadas.");

		}

	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDto objDto) {

		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDto objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		// Cidade cid = repositoryCidade.findById(objDto.getCidadeId()).get();
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}

		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

}
