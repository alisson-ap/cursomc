package com.magalhaes.cursomc.domain.enums;

public enum EstadoPagemento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	
	public int getCod() {
		return cod;
	}


	public void setCod(int cod) {
		this.cod = cod;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	private EstadoPagemento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	

	public static EstadoPagemento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (EstadoPagemento x : EstadoPagemento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invâlido" + cod);
	}
}
