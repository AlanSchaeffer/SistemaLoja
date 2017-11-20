package br.unisinos.desenvsoft3.model.pedido.domain;

import br.unisinos.desenvsoft3.model.generic.domain.EnumDomain;

public enum StatusPedido implements EnumDomain {
	CRIADO("C", "Criado"),
	FATURADO("F", "Faturado"),
	ENVIADO("E", "Enviado"),
	CONCLUIDO("O", "Concluído"),
	CANCELADO("A", "Cancelado");

	private String value;
	private String descricao;
	
	private StatusPedido(String value, String descricao) {
		this.value = value;
		this.descricao = descricao;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return descricao;
	}

	public static StatusPedido byValue(String statusValue) {
		if(statusValue != null) {
			for (StatusPedido statusPedido : values()) {
				if(statusValue.equals(statusPedido.getValue())) {
					return statusPedido;
				}
			}
		}
		
		return null;
	}
}
