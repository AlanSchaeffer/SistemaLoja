package br.unisinos.desenvsoft3.model.pedido.repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.unisinos.desenvsoft3.model.pedido.domain.StatusPedido;

public class PedidoListadoView {

	private Integer id;
	private String cdStatus;
	private String descStatus;
	private Double valorTotal;
	private Integer qtdeItens;
	private String dtPedido;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getQtdeItens() {
		return qtdeItens;
	}

	public void setQtdeItens(Integer qtdeItens) {
		this.qtdeItens = qtdeItens;
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

	public String getCdStatus() {
		return cdStatus;
	}

	public String getDescStatus() {
		return descStatus;
	}
	
	public void setCdStatus(StatusPedido status) {
		cdStatus = status.getValue();
		descStatus = status.toString();
	}
}
