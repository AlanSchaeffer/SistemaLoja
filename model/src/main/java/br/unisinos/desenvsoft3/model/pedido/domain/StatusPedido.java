package br.unisinos.desenvsoft3.model.pedido.domain;

import br.unisinos.desenvsoft3.model.generic.domain.EnumDomain;

public enum StatusPedido implements EnumDomain {
	CRIADO("C"),
	FATURADO("F"),
	ENVIADO("E"),
	CONCLUIDO("O"),
	CANCELADO("A");

	private String value;
	
	private StatusPedido(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}
}
