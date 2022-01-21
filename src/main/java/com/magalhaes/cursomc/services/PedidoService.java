package com.magalhaes.cursomc.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magalhaes.cursomc.domain.Pedido;
import com.magalhaes.cursomc.repositories.PedidodRepository;
//O Service vai usar uma instância do JPA e fazer os metódos para gerenciar o banco de dados
@Service
public class PedidoService {
	
	@Autowired
	private PedidodRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() ->
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName(), null)); 	
	
	}
	
}
