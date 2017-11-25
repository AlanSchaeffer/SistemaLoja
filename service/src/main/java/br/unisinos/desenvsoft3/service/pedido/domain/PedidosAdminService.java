package br.unisinos.desenvsoft3.service.pedido.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.pedido.dao.PedidoDAO;
import br.unisinos.desenvsoft3.model.pedido.domain.Pedido;
import br.unisinos.desenvsoft3.model.pedido.domain.StatusPedido;
import br.unisinos.desenvsoft3.model.pedido.repository.ListagemPedidosRepository;
import br.unisinos.desenvsoft3.model.pedido.repository.PedidoListadoAdminView;
import br.unisinos.desenvsoft3.model.pedido.repository.PedidoView;
import br.unisinos.desenvsoft3.model.pedido.repository.ViewPedidoRepository;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;

@Service
public class PedidosAdminService {

	@Autowired
	private ListagemPedidosRepository listagemPedidosRepository;
	
	@Autowired
	private ViewPedidoRepository viewPedidoRepository;
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	public List<PedidoListadoAdminView> getTodosPedidosAbertos() {
		return listagemPedidosRepository.listarPedidosParaAdministrador();
	}
	
	public PedidoView getPedido(Integer idPedido) {
		PedidoView pedido = viewPedidoRepository.getPedidoAdministrador(idPedido);
		
		if(!pedido.existe()) {
			throw new IllegalArgumentException("Pedido inexistente.");
		}
		
		return pedido;
	}
	
	public GenericResponse cancelarPedido(Integer idPedido) {
		Pedido pedido = pedidoDAO.get(idPedido);
		if(pedido == null) {
			return GenericResponse.error("Pedido inexistente.");
		} else if(!pedido.podeCancelar()) {
			return GenericResponse.error("Pedido não pode ser cancelado pois seu status é " + pedido.getStatusPedido().toString());
		}
		
		pedido.setStatusPedido(StatusPedido.CANCELADO);
		pedidoDAO.salvar(pedido);
		
		return GenericResponse.ok();
	}

	public GenericResponse alterarStatus(Integer idPedido, String novoStatus) {
		StatusPedido status = StatusPedido.byValue(novoStatus);
		if(status == null) {
			return GenericResponse.error("Status inválido.");
		}
		
		Pedido pedido = pedidoDAO.get(idPedido);
		if(pedido == null) {
			return GenericResponse.error("Pedido inexistente.");
		}
		
		pedido.setStatusPedido(status);
		pedidoDAO.salvar(pedido);
		
		return GenericResponse.ok();
	}
}
