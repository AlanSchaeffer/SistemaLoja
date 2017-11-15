package br.unisinos.desenvsoft3.controller.webservice.pedido;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisinos.desenvsoft3.model.pedido.repository.ListaDePedidos;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.pedido.domain.Endereco;
import br.unisinos.desenvsoft3.service.pedido.domain.PedidosService;
import br.unisinos.desenvsoft3.service.pedido.domain.RealizaPedidoRequest;
import br.unisinos.desenvsoft3.sistema.login.UsuarioBean;

@Transactional
@RestController
@RequestMapping("/services/pedidos")
public class PedidoController {

	@Autowired
	private UsuarioBean usuarioBean;
	
	@Autowired
	private PedidosService pedidosService;
	
	@PostMapping("/realizar")
	public GenericResponse realizarPedido(@RequestBody RealizaPedidoRequest request) {
		if(!usuarioBean.isUsuarioAdmin()) {
			Endereco endereco = new Endereco(request.getEndereco());
			return pedidosService.realizarPedidoComCarrinhoDeCompras(endereco, usuarioBean.getIdUsuario());
		}
		return GenericResponse.ok();
	}
	
	@GetMapping("/listar")
	public ListaDePedidos listar() {
		if(usuarioBean.isUsuarioAdmin()) {
			return new ListaDePedidos(new ArrayList<>(pedidosService.getTodosPedidosAbertos()));
		} else {
			return new ListaDePedidos(pedidosService.getPedidosByUsuario(usuarioBean.getIdUsuario()));
		}
	}
}
