package br.unisinos.desenvsoft3.model.pedido.repository;

import java.util.List;

public class ListaDePedidos {

	private List<PedidoListadoView> pedidos;

	public ListaDePedidos(List<PedidoListadoView> pedidos) {
		this.pedidos = pedidos;
	}
	
	public List<PedidoListadoView> getPedidos() {
		return pedidos;
	}
}
