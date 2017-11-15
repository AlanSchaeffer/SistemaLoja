package br.unisinos.desenvsoft3.sistema.login;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
public class UsuarioBean {

	private Integer idUsuario;
	private boolean usuarioAdmin;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isUsuarioAdmin() {
		return usuarioAdmin;
	}

	public void setUsuarioAdmin(boolean usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}
}
