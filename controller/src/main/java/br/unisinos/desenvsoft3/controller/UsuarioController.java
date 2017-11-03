package br.unisinos.desenvsoft3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.login.domain.CadastroUsuarioRequest;
import br.unisinos.desenvsoft3.service.login.domain.CadastroUsuarioService;

@RestController
@RequestMapping("/services/usuario")
public class UsuarioController {
	
//	@Autowired
//	private LoginService loginService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@PostMapping(value="/cadastrar")
	public ResponseEntity<GenericResponse> cadastrar(@RequestBody CadastroUsuarioRequest request) {
		GenericResponse response = cadastroUsuarioService.cadastrar(request);
		return ResponseEntity.ok().body(response);
	}
}
