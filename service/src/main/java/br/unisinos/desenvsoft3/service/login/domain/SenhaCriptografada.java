package br.unisinos.desenvsoft3.service.login.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaCriptografada {

	private String senha;

	public SenhaCriptografada(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return new BCryptPasswordEncoder().encode(senha);
	}
}
