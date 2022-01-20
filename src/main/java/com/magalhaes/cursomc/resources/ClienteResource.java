package com.magalhaes.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magalhaes.cursomc.domain.Cliente;
import com.magalhaes.cursomc.services.ClienteService;

//Controladores Rest, a fase inicial de contato usuários ela usa os métodos do services

@RestController // especificando que a classe é um Controlador REST
@RequestMapping(value = "/clientes") // Aqui criamos a rota para o Requeste
public class ClienteResource {

	@Autowired // Instânciando a classe sem ter que usar um construtor
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // Criando um GET que pega valor com base no ID
	private ResponseEntity<?> find(@PathVariable Integer id) {

		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}

}
