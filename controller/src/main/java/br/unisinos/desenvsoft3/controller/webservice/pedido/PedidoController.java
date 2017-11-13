package br.unisinos.desenvsoft3.controller.webservice.pedido;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisinos.desenvsoft3.sistema.login.UsuarioBean;

@Transactional
@RestController
@RequestMapping("/services/pedidos")
public class PedidoController {

	@Autowired
	private UsuarioBean usuarioBean;
	
	@PostMapping("/teste")
	public String meusPedidos() {
		return usuarioBean.getIdUsuario().toString();
	}
}
