package br.unisinos.desenvsoft3.service.pedido.domain;

public class Endereco {

	private String txEndereco;

	public Endereco(String txEndereco) {
		this.txEndereco = txEndereco;
	}
	
	public String getTxEndereco() {
		return txEndereco;
	}
}
