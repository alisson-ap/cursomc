package com.magalhaes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magalhaes.cursomc.domain.Produto;
import com.magalhaes.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto buscar(Integer id) {
		Optional<Produto> prod = produtoRepository.findById(id);
		return prod.orElse(null);
		
	}
}
