package br.unisinos.desenvsoft3.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.login.dao.UsuarioDAO;
import br.unisinos.desenvsoft3.model.login.domain.Usuario;

@Service
public class LoginService {

	@Resource
	private UsuarioDAO usuarioDAO;
	
	public Integer getContagem() {
		return usuarioDAO.conta();
	}
	
	public List<String> getNomes() {
		return usuarioDAO.listarNomes();
	}

	public void salvar(Usuario usuario) {
		usuarioDAO.save(usuario);
	}
}
