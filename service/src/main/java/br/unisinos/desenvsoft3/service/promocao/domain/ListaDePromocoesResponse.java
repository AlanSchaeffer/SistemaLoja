package br.unisinos.desenvsoft3.service.promocao.domain;

import java.util.List;

import br.unisinos.desenvsoft3.model.promocao.repository.PromocaoView;

public class ListaDePromocoesResponse {

	private List<PromocaoView> promocoes;

	public ListaDePromocoesResponse(List<PromocaoView> promocoes) {
		this.promocoes = promocoes;
	}
	
	public List<PromocaoView> getPromocoes() {
		return promocoes;
	}
}
