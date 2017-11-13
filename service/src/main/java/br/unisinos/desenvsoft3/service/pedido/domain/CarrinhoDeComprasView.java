package br.unisinos.desenvsoft3.service.pedido.domain;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeComprasView {

	private Double valorTotal = 0D;
	private List<CarrinhoDeComprasItemView> itens = new ArrayList<>();

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<CarrinhoDeComprasItemView> getItens() {
		return itens;
	}

	public void setItens(List<CarrinhoDeComprasItemView> itens) {
		this.itens = itens;
	}
	
	public void atualizaValorTotal() {
		setValorTotal(getItens().stream()
								.mapToDouble(item -> item.getValorProduto() * item.getQuantidade())
								.sum());
	}
}
