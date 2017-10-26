package br.unisinos.desenvsoft3.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
