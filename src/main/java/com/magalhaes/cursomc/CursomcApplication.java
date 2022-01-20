package com.magalhaes.cursomc;

import java.text.SimpleDateFormat;
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
import com.magalhaes.cursomc.domain.Pagamento;
import com.magalhaes.cursomc.domain.PagamentoComBoleto;
import com.magalhaes.cursomc.domain.PagamentoComCartao;
import com.magalhaes.cursomc.domain.Pedido;
import com.magalhaes.cursomc.domain.Produto;
import com.magalhaes.cursomc.domain.enums.EstadoPagemento;
import com.magalhaes.cursomc.domain.enums.TipoCliente;
import com.magalhaes.cursomc.repositories.CategoriaRepository;
import com.magalhaes.cursomc.repositories.CidadeRepository;
import com.magalhaes.cursomc.repositories.ClienteRepository;
import com.magalhaes.cursomc.repositories.EnderecoRepository;
import com.magalhaes.cursomc.repositories.EstadoRepository;
import com.magalhaes.cursomc.repositories.PagamentoRepository;
import com.magalhaes.cursomc.repositories.PedidodRepository;
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
	
	@Autowired
	private PedidodRepository RepoPed;
	
	@Autowired
	private PagamentoRepository RepoPag;

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
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:52"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagemento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagemento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		RepoPed.saveAll(Arrays.asList(ped1,ped2));
		RepoPag.saveAll(Arrays.asList(pagto1,pagto2));
		
		
	}

}
