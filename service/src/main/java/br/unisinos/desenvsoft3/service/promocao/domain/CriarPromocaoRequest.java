package br.unisinos.desenvsoft3.service.promocao.domain;

import java.util.Date;
import java.util.List;

public class CriarPromocaoRequest {

	private List<Integer> idsProdutos;
	private Date dtInicio;
	private Date dtFim;
	private Double peDesconto;

	public List<Integer> getIdsProdutos() {
		return idsProdutos;
	}

	public void setIdsProdutos(List<Integer> idsProdutos) {
		this.idsProdutos = idsProdutos;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public Double getPeDesconto() {
		return peDesconto;
	}

	public void setPeDesconto(Double peDesconto) {
		this.peDesconto = peDesconto;
	}
}
