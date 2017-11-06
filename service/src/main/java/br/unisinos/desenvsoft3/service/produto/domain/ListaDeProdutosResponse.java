package br.unisinos.desenvsoft3.service.produto.domain;

import java.util.List;

import br.unisinos.desenvsoft3.model.produto.domain.ProdutoListado;

public class ListaDeProdutosResponse {

	public ListaDeProdutosResponse(List<ProdutoListado> produtos) {
		this.produtos = produtos;
	}
	
	private List<ProdutoListado> produtos;
	
	public List<ProdutoListado> getProdutos() {
		return produtos;
	}
}
