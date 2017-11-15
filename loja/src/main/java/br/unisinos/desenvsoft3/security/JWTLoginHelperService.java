package br.unisinos.desenvsoft3.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unisinos.desenvsoft3.service.login.domain.LoginService;

@Component
public class JWTLoginHelperService {

	@Autowired
	private ContaAdministrativa contaAdministrativa;
	
	@Autowired
	private LoginService loginService;

	public boolean isUsuarioAdministrador(String usernameOrEmail) {
		return contaAdministrativa.isUsuarioAdministrador(usernameOrEmail);
	}
	
	public Integer getIdUsuario(String usernameOrEmail) {
		if(isUsuarioAdministrador(usernameOrEmail)) {
			return -1;
		} else {
			return loginService.getIdUsuarioByName(usernameOrEmail);
		}
	}
}
