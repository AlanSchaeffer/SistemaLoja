package br.unisinos.desenvsoft3.controller.webservice.pedido;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.pedido.domain.CarrinhoDeComprasService;
import br.unisinos.desenvsoft3.service.pedido.domain.CarrinhoDeComprasView;
import br.unisinos.desenvsoft3.sistema.login.UsuarioBean;

@Transactional
@RestController
@RequestMapping("/services/carrinho")
public class CarrinhoDeComprasController {

	@Autowired
	private UsuarioBean usuarioBean;
	
	@Autowired
	private CarrinhoDeComprasService carrinhoDeComprasService;
	
	@GetMapping("/")
	public CarrinhoDeComprasView getCarrinho() {
		if(usuarioBean.isUsuarioAdmin()) {
			return new CarrinhoDeComprasView(); // administrador nao tem carrinho
		}
		
		return carrinhoDeComprasService.getCarrinhoDeCompras(usuarioBean.getIdUsuario());
	}
	
	@PostMapping("/add/{idProduto}")
	public GenericResponse adicionarAoCarrinho(@PathVariable(name = "idProduto") Integer idProduto) {
		if(!usuarioBean.isUsuarioAdmin()) {
			return carrinhoDeComprasService.adicionarAoCarrinhoDeCompras(idProduto, usuarioBean.getIdUsuario());
		}
		return GenericResponse.ok();
	}
	
	@PostMapping("/remove/{idProduto}")
	public GenericResponse removerDoCarrinho(@PathVariable(name = "idProduto") Integer idProduto) {
		if(!usuarioBean.isUsuarioAdmin()) {
			return carrinhoDeComprasService.removerDoCarrinhoDeCompras(idProduto, usuarioBean.getIdUsuario());
		}
		return GenericResponse.ok();
	}
	
	@PostMapping("/alterarQuantidade/{idProduto}/{quantidade}")
	public GenericResponse alterarQuantidadeDeProduto(@PathVariable(name = "idProduto") Integer idProduto, @PathVariable(name = "quantidade") Integer quantidade) {
		if(!usuarioBean.isUsuarioAdmin()) {
			return carrinhoDeComprasService.alterarQuantidadeDeProduto(idProduto, quantidade, usuarioBean.getIdUsuario());
		}
		return GenericResponse.ok();
	}
}
