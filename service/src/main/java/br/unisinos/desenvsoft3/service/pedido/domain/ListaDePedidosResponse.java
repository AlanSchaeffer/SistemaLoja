package br.unisinos.desenvsoft3.service.pedido.domain;

import java.util.List;

import br.unisinos.desenvsoft3.model.pedido.repository.PedidoListadoView;

public class ListaDePedidosResponse {

	private List<PedidoListadoView> pedidos;

	public ListaDePedidosResponse(List<PedidoListadoView> pedidos) {
		this.pedidos = pedidos;
	}
	
	public List<PedidoListadoView> getPedidos() {
		return pedidos;
	}
}
