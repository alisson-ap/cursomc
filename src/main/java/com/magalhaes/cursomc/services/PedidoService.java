package com.magalhaes.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magalhaes.cursomc.domain.ItemPedido;
import com.magalhaes.cursomc.domain.PagamentoComBoleto;
import com.magalhaes.cursomc.domain.Pedido;
import com.magalhaes.cursomc.domain.enums.EstadoPagemento;
import com.magalhaes.cursomc.repositories.ItemPedidoRepository;
import com.magalhaes.cursomc.repositories.PagamentoRepository;
import com.magalhaes.cursomc.repositories.PedidodRepository;
//O Service vai usar uma instância do JPA e fazer os metódos para gerenciar o banco de dados
@Service
public class PedidoService {
	
	@Autowired
	private PedidodRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository repoPagamento;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository repoItempedido;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() ->
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName(), null)); 	
	
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagemento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		repoPagamento.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.buscar(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		repoItempedido.saveAll(obj.getItens());
		return obj;
	}
}
