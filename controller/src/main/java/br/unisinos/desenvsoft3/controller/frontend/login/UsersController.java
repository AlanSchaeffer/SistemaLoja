package br.unisinos.desenvsoft3.controller.frontend.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {
	@RequestMapping("/users")
	public String index() {
		return "/index.html";
	}
}
