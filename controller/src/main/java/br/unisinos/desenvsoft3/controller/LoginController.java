package br.unisinos.desenvsoft3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping(value="/")
	@ResponseBody
	public String index() {
		return "Teste!";
	}
}
