package br.unisinos.desenvsoft3.controller.webservice.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisinos.desenvsoft3.model.produto.domain.ProdutoFilter;
import br.unisinos.desenvsoft3.model.produto.domain.ProdutoView;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.produto.domain.CadastroProdutoRequest;
import br.unisinos.desenvsoft3.service.produto.domain.ListaDeProdutosResponse;
import br.unisinos.desenvsoft3.service.produto.domain.ProdutoService;

@RestController
@RequestMapping("/services/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping("/salvar")
	public GenericResponse salvar(@RequestBody CadastroProdutoRequest request) {
		return produtoService.salvar(request);
	}
	
	@RequestMapping("/listar")
	public ListaDeProdutosResponse listar(@RequestBody ProdutoFilter filtro) {
		return new ListaDeProdutosResponse(produtoService.listarProdutos(new ProdutoFilter()));
	}
	
	@GetMapping("/{id}")
	public ProdutoView visualizar(@PathVariable("id") Integer id) {
		return produtoService.visualizar(id);
	}
}
