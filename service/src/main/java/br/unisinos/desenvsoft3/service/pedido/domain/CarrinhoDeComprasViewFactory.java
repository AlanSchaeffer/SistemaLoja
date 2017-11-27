package br.unisinos.desenvsoft3.service.pedido.domain;

import br.unisinos.desenvsoft3.model.pedido.domain.CarrinhoDeCompras;
import br.unisinos.desenvsoft3.model.pedido.domain.CarrinhoDeComprasItem;
import br.unisinos.desenvsoft3.service.produto.domain.ValorProdutoService;

public class CarrinhoDeComprasViewFactory {

	private ValorProdutoService valorProdutoService;
	
	public CarrinhoDeComprasViewFactory with(ValorProdutoService valorProdutoService) {
		this.valorProdutoService = valorProdutoService;
		return this;
	}
	
	public CarrinhoDeComprasView create(CarrinhoDeCompras carrinho) {
		CarrinhoDeComprasView view = new CarrinhoDeComprasView();
		
		carrinho.getItens()
				.stream()
				.map(this::createItem)
				.forEach(view.getItens()::add);
		
		view.atualizaValorTotal();
		return view;
	}
	
	private CarrinhoDeComprasItemView createItem(CarrinhoDeComprasItem item) {
		CarrinhoDeComprasItemView view = new CarrinhoDeComprasItemView();
		view.setIdProduto(item.getProduto().getId());
		view.setNmProduto(item.getProduto().getNome());
		view.setValorProduto(item.getProduto().getPreco());
		view.setValorDescontado(valorProdutoService.getValorProduto(view.getIdProduto()));
		view.setQuantidade(item.getQuantidade());
		return view;
	}
}
