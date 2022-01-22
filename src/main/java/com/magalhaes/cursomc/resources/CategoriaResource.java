package com.magalhaes.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.magalhaes.cursomc.domain.Categoria;
import com.magalhaes.cursomc.services.CategoriaService;

//Controladores Rest, a fase inicial de contato usuários ela usa os métodos do services

@RestController // especificando que a classe é um Controlador REST
@RequestMapping(value = "/categorias") // Aqui criamos a rota para o Requeste
public class CategoriaResource {

	@Autowired // Instânciando a classe sem ter que usar um construtor
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // Criando um GET que pega valor com base no ID
	private ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
