package com.magalhaes.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magalhaes.cursomc.domain.Cliente;
import com.magalhaes.cursomc.repositories.ClienteRepository;
import com.magalhaes.cursomc.services.exceptions.ObjectNotFoundException;
//O Service vai usar uma instância do JPA e fazer os metódos para gerenciar o banco de dados
@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() ->
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName(), null)); 	
	
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
}
