package com.magalhaes.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.magalhaes.cursomc.domain.Categoria;
import com.magalhaes.cursomc.domain.Cidade;
import com.magalhaes.cursomc.domain.Cliente;
import com.magalhaes.cursomc.domain.Endereco;
import com.magalhaes.cursomc.domain.Estado;
import com.magalhaes.cursomc.domain.Produto;
import com.magalhaes.cursomc.domain.enums.TipoCliente;
import com.magalhaes.cursomc.repositories.CategoriaRepository;
import com.magalhaes.cursomc.repositories.CidadeRepository;
import com.magalhaes.cursomc.repositories.ClienteRepository;
import com.magalhaes.cursomc.repositories.EnderecoRepository;
import com.magalhaes.cursomc.repositories.EstadoRepository;
import com.magalhaes.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository RepoCat;
	
	@Autowired
	private ProdutoRepository RepoProd;
	
	@Autowired
	private CidadeRepository RepoCid;
	
	@Autowired
	private EstadoRepository RepoEst;
	
	@Autowired
	private ClienteRepository RepoCli;
	
	@Autowired
	private EnderecoRepository RepoEnd;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"mouse",80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().add(cat1);
		
		Estado est1 = new Estado(null, "Minais Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		RepoCat.saveAll(Arrays.asList(cat1,cat2));
		RepoProd.saveAll(Arrays.asList(p1,p2,p3));
		RepoEst.saveAll(Arrays.asList(est1,est2));
		RepoCid.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678945", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("40028922", "40022289"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "apt 303", "jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "casa a", "centro", "38220834", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		

		RepoCli.saveAll(Arrays.asList(cli1));
		RepoEnd.saveAll(Arrays.asList(e1,e2));
		
		
	}

}
