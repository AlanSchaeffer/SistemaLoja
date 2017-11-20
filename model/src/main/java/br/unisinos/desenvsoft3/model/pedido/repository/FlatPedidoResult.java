package br.unisinos.desenvsoft3.model.pedido.repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.unisinos.desenvsoft3.model.pedido.domain.StatusPedido;

public class FlatPedidoResult {

	private Integer idPedido;
	private String dtPedido;
	private Double vlFrete;
	private StatusPedido statusPedido;
	private String enderecoEntrega;
	private String nmUsuario;

	private Integer idPedidoItem;
	private String nmProduto;
	private Double valor;
	private Integer quantidade;

	public Integer getIdPedido() {
		return idPedido;
	}
	
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(Date dtPedidoAsDate) {
		if(dtPedidoAsDate == null) {
			this.dtPedido = null;
		} else {
			this.dtPedido = new SimpleDateFormat("dd/MM/yyyy").format(dtPedidoAsDate);
		}
	}

	public Double getVlFrete() {
		return vlFrete;
	}

	public void setVlFrete(Double vlFrete) {
		this.vlFrete = vlFrete;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
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

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}
}
