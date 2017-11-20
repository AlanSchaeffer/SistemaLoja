package br.unisinos.desenvsoft3.model.pedido.repository;

import java.util.ArrayList;
import java.util.List;

public class PedidoView {

	private Integer id;
	private String dtPedido;
	private Double vlFrete;
	private String cdStatusPedido;
	private String descStatusPedido;
	private String enderecoEntrega;
	private List<PedidoItemView> itens = new ArrayList<>();
	
	public PedidoView(List<FlatPedidoResult> results) {
		if(!results.isEmpty()) {
			FlatPedidoResult exemplo = results.get(0);
			id = exemplo.getIdPedido();
			dtPedido = exemplo.getDtPedido();
			vlFrete = exemplo.getVlFrete();
			cdStatusPedido = exemplo.getStatusPedido().getValue();
			descStatusPedido = exemplo.getStatusPedido().toString();
			enderecoEntrega = exemplo.getEnderecoEntrega();
			
			results.stream()
				   .map(PedidoItemView::new)
				   .forEach(itens::add);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}

	public Double getVlFrete() {
		return vlFrete;
	}

	public void setVlFrete(Double vlFrete) {
		this.vlFrete = vlFrete;
	}

	public String getCdStatusPedido() {
		return cdStatusPedido;
	}

	public void setCdStatusPedido(String cdStatusPedido) {
		this.cdStatusPedido = cdStatusPedido;
	}

	public String getDescStatusPedido() {
		return descStatusPedido;
	}

	public void setDescStatusPedido(String descStatusPedido) {
		this.descStatusPedido = descStatusPedido;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public List<PedidoItemView> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItemView> itens) {
		this.itens = itens;
	}
	
	public boolean existe() {
		return id != null;
	}
}
