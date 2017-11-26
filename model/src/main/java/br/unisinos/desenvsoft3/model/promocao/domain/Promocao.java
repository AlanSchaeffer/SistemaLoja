package br.unisinos.desenvsoft3.model.promocao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.unisinos.desenvsoft3.model.produto.domain.Produto;

@Entity
@Table(name = "PROMOCOES")
public class Promocao {

	@Id
	@GenericGenerator(name = "SEQ_PROMOCOES", strategy = "sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROMOCOES")
	@Column(name = "ID")
	private Integer id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO")
	private Produto produto;
	
	@NotNull
	@Column(name = "DT_PEDIDO")
	private Date dtInicial;
	
	@NotNull
	@Column(name = "DT_PEDIDO")
	private Date dtFinal;
	
	@NotNull
	@Column(name = "PE_DESCONTO")
	private Double peDesconto;
	
	@Version
	@Column(name = "VERSION")
	private Integer version;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Integer getId() {
		return id;
	}

	public Double getPeDesconto() {
		return peDesconto;
	}

	public void setPeDesconto(Double peDesconto) {
		this.peDesconto = peDesconto;
	}
}
