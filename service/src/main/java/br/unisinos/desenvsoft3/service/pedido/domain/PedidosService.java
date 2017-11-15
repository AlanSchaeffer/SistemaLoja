package br.unisinos.desenvsoft3.service.pedido.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.pedido.dao.CarrinhoDeComprasDAO;
import br.unisinos.desenvsoft3.model.pedido.dao.PedidoDAO;
import br.unisinos.desenvsoft3.model.pedido.domain.CarrinhoDeCompras;
import br.unisinos.desenvsoft3.model.pedido.domain.Pedido;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;
import br.unisinos.desenvsoft3.service.produto.domain.ValorProdutoService;

@Service
public class PedidosService {

	@Autowired
	private CarrinhoDeComprasDAO carrinhoDeComprasDAO;
	
	@Autowired
	private FreteService freteService;

	@Autowired
	private ValorProdutoService valorProdutoService;
	
	@Autowired
	private PedidoDAO pedidoDAO;
	
	public GenericResponse realizarPedidoComCarrinhoDeCompras(Endereco endereco, Integer idUsuario) {
		if(StringUtils.isBlank(endereco.getTxEndereco())) {
			return GenericResponse.error("Endereço deve estar preenchido.");
		}
		
		CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasDAO.getByUsuario(idUsuario);
		Pedido pedido = new PedidoFactory().endereco(endereco)
										   .withFreteService(freteService)
										   .withValorProdutoService(valorProdutoService)
										   .create(carrinhoDeCompras);
		
		pedidoDAO.salvar(pedido);
		carrinhoDeComprasDAO.delete(carrinhoDeCompras);
		
		return GenericResponse.ok();
	}
}
