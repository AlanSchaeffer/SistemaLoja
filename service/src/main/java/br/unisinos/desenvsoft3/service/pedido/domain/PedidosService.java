package br.unisinos.desenvsoft3.service.pedido.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.pedido.dao.CarrinhoDeComprasDAO;
import br.unisinos.desenvsoft3.model.pedido.dao.PedidoDAO;
import br.unisinos.desenvsoft3.model.pedido.domain.CarrinhoDeCompras;
import br.unisinos.desenvsoft3.model.pedido.domain.Pedido;
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
	
	public void realizarPedidoComCarrinhoDeCompras(Endereco endereco, Integer idUsuario) {
		CarrinhoDeCompras carrinhoDeCompras = carrinhoDeComprasDAO.getByUsuario(idUsuario);
		Pedido pedido = new PedidoFactory().endereco(endereco)
										   .withFreteService(freteService)
										   .withValorProdutoService(valorProdutoService)
										   .create(carrinhoDeCompras);
		
		pedidoDAO.salvar(pedido);
		carrinhoDeComprasDAO.delete(carrinhoDeCompras);
	}
}
