package br.unisinos.desenvsoft3.model.produto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "PRODUTOS")
public class Produto {

	@Id
	@GenericGenerator(name = "SEQ_PRODUTOS", strategy = "sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTOS")
	@Column(name = "ID")
	private Integer id;

	@NotBlank
	@Column(name = "NOME")
	private String nome;

	@NotNull
	@Column(name = "PRECO")
	private Double preco;

	@NotBlank
	@Column(name = "DESCRICAO", length = 6000)
	private String descricao;

	@NotNull
	@Column(name = "ESTOQUE")
	private Integer estoque;

	@Version
	@Column(name = "VERSION")
	private Integer version;

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
}
