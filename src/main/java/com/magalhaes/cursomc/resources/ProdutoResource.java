package com.magalhaes.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magalhaes.cursomc.domain.Produto;
import com.magalhaes.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService prodServ;
	
@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<?> show(@PathVariable Integer id){
		Produto prod = prodServ.buscar(id);
		return ResponseEntity.ok().body(prod);
	}
	
}
