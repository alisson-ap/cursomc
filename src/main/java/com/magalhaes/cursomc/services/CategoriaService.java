package com.magalhaes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.magalhaes.cursomc.domain.Categoria;
import com.magalhaes.cursomc.repositories.CategoriaRepository;
import com.magalhaes.cursomc.services.exceptions.DateIntegrityViolationException;
import com.magalhaes.cursomc.services.exceptions.ObjectNotFoundException;

//O Service vai usar uma instância do JPA e fazer os metódos para gerenciar o banco de dados
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null));

	}

	// Método de inserção que vai usado no Controller
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DateIntegrityViolationException("Não é possível excluir uma categoria que possuí produto");
		}
	}

}
