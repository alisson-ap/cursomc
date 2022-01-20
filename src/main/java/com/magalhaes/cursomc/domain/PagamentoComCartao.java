package com.magalhaes.cursomc.domain;

import javax.persistence.Entity;

import com.magalhaes.cursomc.domain.enums.EstadoPagemento;


@Entity
public class PagamentoComCartao extends Pagamento{
	
	private static final long serialVersionUID = 1L;

	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartao() {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagemento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		// TODO Auto-generated constructor stub
		
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

}
