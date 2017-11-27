package br.unisinos.desenvsoft3.controller.webservice.promocao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.promocao.domain.CriarPromocaoRequest;
import br.unisinos.desenvsoft3.service.promocao.domain.ListaDePromocoesResponse;
import br.unisinos.desenvsoft3.service.promocao.domain.PromocaoService;

@Transactional
@RestController
@RequestMapping("/services/promocao")
public class PromocaoController {

	@Autowired
	private PromocaoService promocaoService;
	
	@PostMapping("/criar")
	public GenericResponse criarPromocao(@RequestBody CriarPromocaoRequest request) {
		return promocaoService.criarPromocao(request);
	}
	
	@GetMapping("/listar")
	public ListaDePromocoesResponse listarPromocoes() {
		return new ListaDePromocoesResponse(promocaoService.listarPromocoes());
	}
	
	@GetMapping("/remover/{idPromocao}")
	public GenericResponse removerPromocao(@PathVariable("idPromocao") Integer idPromocao) {
		return promocaoService.removerPromocao(idPromocao);
	}
}
