package br.unisinos.desenvsoft3.controller.webservice.pedido;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisinos.desenvsoft3.model.pedido.repository.PedidoView;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.pedido.domain.Endereco;
import br.unisinos.desenvsoft3.service.pedido.domain.ListaDePedidosResponse;
import br.unisinos.desenvsoft3.service.pedido.domain.PedidosAdminService;
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
	
	@Autowired
	private PedidosAdminService pedidosAdminService;
	
	@PostMapping("/realizar")
	public GenericResponse realizarPedido(@RequestBody RealizaPedidoRequest request) {
		if(!usuarioBean.isUsuarioAdmin()) {
			Endereco endereco = new Endereco(request.getEndereco());
			return pedidosService.realizarPedidoComCarrinhoDeCompras(endereco, usuarioBean.getIdUsuario());
		}
		return GenericResponse.ok();
	}
	
	@GetMapping("/listar")
	public ListaDePedidosResponse listar() {
		if(usuarioBean.isUsuarioAdmin()) {
			return new ListaDePedidosResponse(new ArrayList<>(pedidosAdminService.getTodosPedidosAbertos()));
		} else {
			return new ListaDePedidosResponse(pedidosService.getPedidosByUsuario(usuarioBean.getIdUsuario()));
		}
	}
	
	@GetMapping("/{idPedido}")
	public PedidoView getPedido(@PathVariable("idPedido") Integer idPedido) {
		if(usuarioBean.isUsuarioAdmin()) {
			return pedidosAdminService.getPedido(idPedido);
		} else {
			return pedidosService.getPedido(idPedido, usuarioBean.getIdUsuario());
		}
	}
	

	@GetMapping("/cancelar/{idPedido}")
	public GenericResponse cancelarPedido(@PathVariable("idPedido") Integer idPedido) {
		if(usuarioBean.isUsuarioAdmin()) {
			return pedidosAdminService.cancelarPedido(idPedido);
		} else {
			return pedidosService.cancelarPedido(idPedido, usuarioBean.getIdUsuario());
		}
	}
	
	@GetMapping("/alterarStatus/{idPedido}/{novoStatus}")
	public GenericResponse alterarStatus(@PathVariable("idPedido") Integer idPedido, @PathVariable("novoStatus") String novoStatus) {
		return pedidosAdminService.alterarStatus(idPedido, novoStatus);
	}
}
