package br.unisinos.desenvsoft3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping("/admin")
	public String index() {
		return "../index.html";
	}
}