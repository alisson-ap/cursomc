package com.magalhaes.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magalhaes.cursomc.domain.Cidade;

public interface CidadeRepository  extends JpaRepository<Cidade, Integer>{

}
