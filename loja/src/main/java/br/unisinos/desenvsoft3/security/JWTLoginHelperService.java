package br.unisinos.desenvsoft3.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.unisinos.desenvsoft3.service.login.domain.LoginService;
import br.unisinos.desenvsoft3.sistema.login.UsuarioBean;

@Component
public class JWTLoginHelperService {

	@Autowired
	private ContaAdministrativa contaAdministrativa;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ActiveSessionsMap activeSessionsMap;
	
	@Autowired
	private UsuarioBean usuarioBean;

	public void registrarSessao(String token, Integer idUsuario, String role) {
		activeSessionsMap.registrar(token, idUsuario, role);
	}
	
	public List<GrantedAuthority> buildRolesListForLoginToken(String token) {
		if(token == null) {
			return Collections.emptyList();
		} else {
			String role = activeSessionsMap.getRole(token);
			if(role == null) {
				return Collections.emptyList();
			}
			
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			return Arrays.asList(authority);
		}
	}
	
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

	public void preencheUsuarioBean(String token) {
		usuarioBean.setIdUsuario(activeSessionsMap.getIdUsuario(token));
		usuarioBean.setUsuarioAdmin("ROLE_ADMIN".equals(activeSessionsMap.getRole(token)));
	}
}
