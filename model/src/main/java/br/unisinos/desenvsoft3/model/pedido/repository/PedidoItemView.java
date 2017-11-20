package br.unisinos.desenvsoft3.model.pedido.repository;

public class PedidoItemView {

	private Integer idPedidoItem;
	private String nmProduto;
	private Double valor;
	private Integer quantidade;

	public PedidoItemView(FlatPedidoResult result) {
		idPedidoItem = result.getIdPedidoItem();
		nmProduto = result.getNmProduto();
		valor = result.getValor();
		quantidade = result.getQuantidade();
	}
	
	public Integer getIdPedidoItem() {
		return idPedidoItem;
	}

	public void setIdPedidoItem(Integer idPedidoItem) {
		this.idPedidoItem = idPedidoItem;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
