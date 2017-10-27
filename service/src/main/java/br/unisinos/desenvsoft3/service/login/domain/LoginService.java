package br.unisinos.desenvsoft3.service.login.domain;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.login.dao.UsuarioDAO;

@Service
public class LoginService {

	@Resource
	private UsuarioDAO usuarioDAO;
	
}
