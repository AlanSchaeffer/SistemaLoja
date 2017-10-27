package br.unisinos.desenvsoft3.service.login.domain;

import org.apache.commons.codec.digest.DigestUtils;

public class SenhaCriptografada {

	private String senha;

	public SenhaCriptografada(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return DigestUtils.md5Hex(senha);
	}
}
