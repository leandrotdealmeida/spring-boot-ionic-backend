package com.trovilho.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trovilho.cursomc.domain.Endereco;
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
