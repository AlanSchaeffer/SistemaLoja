package br.unisinos.desenvsoft3.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Component;

@Component
public class ContaAdministrativa {
	
	private final String userName = "administraSys";
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser(userName)
			.password("sysloja999")
			.roles("ADMIN");
	}
	
	public boolean isUsuarioAdministrador(String tokenName) {
		return userName.equals(tokenName);
	}
}
