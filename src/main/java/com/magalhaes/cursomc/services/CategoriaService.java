package com.magalhaes.cursomc.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magalhaes.cursomc.domain.Categoria;
import com.magalhaes.cursomc.repositories.CategoriaRepository;
//O Service vai usar uma instância do JPA e fazer os metódos para gerenciar o banco de dados
@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() ->
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null)); 	
	
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
}
