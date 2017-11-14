package br.unisinos.desenvsoft3.service.pedido.domain;

import org.springframework.util.Assert;

import br.unisinos.desenvsoft3.model.pedido.domain.CarrinhoDeCompras;
import br.unisinos.desenvsoft3.model.pedido.domain.CarrinhoDeComprasItem;
import br.unisinos.desenvsoft3.model.pedido.domain.Pedido;
import br.unisinos.desenvsoft3.model.pedido.domain.PedidoItem;
import br.unisinos.desenvsoft3.service.produto.domain.ValorProdutoService;

public class PedidoFactory {

	private Endereco endereco;
	private FreteService freteService;
	private ValorProdutoService valorProdutoService;
	
	public PedidoFactory endereco(Endereco endereco) {
		this.endereco = endereco;
		return this;
	}
	
	public PedidoFactory withFreteService(FreteService freteService) {
		this.freteService = freteService;
		return this;
	}
	
	public PedidoFactory withValorProdutoService(ValorProdutoService valorProdutoService) {
		this.valorProdutoService = valorProdutoService;
		return this;
	}
	
	public Pedido create(CarrinhoDeCompras carrinhoDeCompras) {
		Assert.notNull(endereco, "Endereco");
		Assert.notNull(freteService, "FreteService");
		Assert.notNull(valorProdutoService, "ValorProdutoService");
		
		Pedido pedido = new Pedido();
		pedido.setUsuario(carrinhoDeCompras.getUsuario());
		pedido.setVlFrete(freteService.getVlFreteParaEndereco(endereco));
		pedido.setEnderecoEntrega(endereco.getTxEndereco());
		
		carrinhoDeCompras.getItens()
						 .stream()
						 .map(this::toPedidoItem)
						 .forEach(pedido::addItem);
		
		return pedido;
	}
	
	private PedidoItem toPedidoItem(CarrinhoDeComprasItem itemCarrinho) {
		PedidoItem item = new PedidoItem();
		item.setProduto(itemCarrinho.getProduto());
		item.setQuantidade(itemCarrinho.getQuantidade());
		item.setValor(valorProdutoService.getValorProduto(itemCarrinho.getProduto().getId()));
		return item;
	}
}
