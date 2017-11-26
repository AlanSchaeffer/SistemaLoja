package br.unisinos.desenvsoft3.model.promocao.repository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PromocaoView {

	private Integer id;
	private String nmProduto;
	private String dtInicial;
	private String dtFinal;
	private Double vlProdutoOriginal;
	private Double peDesconto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}

	public String getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicialAsDate) {
		if(dtInicialAsDate == null) {
			this.dtInicial = null;
		} else {
			this.dtInicial = new SimpleDateFormat("dd/MM/yyyy").format(dtInicialAsDate);
		}
	}

	public String getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinalAsDate) {
		if(dtFinalAsDate == null) {
			this.dtFinal = null;
		} else {
			this.dtFinal = new SimpleDateFormat("dd/MM/yyyy").format(dtFinalAsDate);
		}
	}

	public Double getVlProdutoOriginal() {
		return vlProdutoOriginal;
	}

	public void setVlProdutoOriginal(Double vlProdutoOriginal) {
		this.vlProdutoOriginal = vlProdutoOriginal;
	}

	public Double getPeDesconto() {
		return peDesconto;
	}

	public void setPeDesconto(Double peDesconto) {
		this.peDesconto = peDesconto;
	}
	
	public boolean isEncerrada() {
		if(dtFinal == null) return true;
		LocalDate dtFinalAsLocalDate = LocalDate.parse(dtFinal, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return dtFinalAsLocalDate.isBefore(LocalDate.now());
	}
}
