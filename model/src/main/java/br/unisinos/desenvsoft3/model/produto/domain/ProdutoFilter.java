package br.unisinos.desenvsoft3.model.produto.domain;

public class ProdutoFilter {

	private String nome;
	private Boolean somenteComEstoque;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNomeFilterWithWildcards() {
		if(nome != null) {
			return "%" + nome + "%";
		}
		return null;
	}

	public Boolean getSomenteComEstoque() {
		return somenteComEstoque;
	}

	public void setSomenteComEstoque(Boolean somenteComEstoque) {
		this.somenteComEstoque = somenteComEstoque;
	}
}
