package br.unisinos.desenvsoft3.model.produto.repository;

public class ProdutoListado {

	private Integer id;
	private String nome;
	private Double preco;
	private boolean temEstoque;
	private Integer estoque;
	private Double precoCheio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public boolean isTemEstoque() {
		return temEstoque;
	}

	public void setTemEstoque(boolean temEstoque) {
		this.temEstoque = temEstoque;
	}
	
	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Double getPrecoCheio() {
		return precoCheio;
	}

	public void setPrecoCheio(Double precoCheio) {
		this.precoCheio = precoCheio;
	}
}
