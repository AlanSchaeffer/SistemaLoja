package br.unisinos.desenvsoft3.controller;

import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unisinos.desenvsoft3.model.login.domain.Usuario;
import br.unisinos.desenvsoft3.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	@RequestMapping(value="/")
	@ResponseBody
	public String index() {
		return "Teste!";
	}
	
	@RequestMapping(value="/contar")
	@ResponseBody
	public String contarUsuarios() {
		return "Temos " + loginService.getContagem() + " usuarios!";
	}
	
	@GetMapping(value="/testar")
	@ResponseBody
	public void testaAdiciona(@RequestParam(name="nome") String nome) {
		Usuario usuario = new Usuario();
		usuario.setName(nome);
		usuario.setPassword(nome);
		usuario.setEmail(nome);
		loginService.salvar(usuario);
	}
	
	@GetMapping(value="/listar")
	@ResponseBody
	public String listarUsuarios() {
		return "Usuarios <br/>"
				+ loginService.getNomes().stream().collect(Collectors.joining("<br/>"));
	}
}
