package br.unisinos.desenvsoft3.controller.webservice.produto;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;

@RestController
@RequestMapping("/services/produtos")
public class ProdutoController {

	public GenericResponse salvar() {
		return null;
	}
	
	public Object carregar() {
		return null;
	}
}
