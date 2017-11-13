package br.unisinos.desenvsoft3.service.pedido.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisinos.desenvsoft3.model.login.dao.UsuarioDAO;
import br.unisinos.desenvsoft3.model.pedido.dao.CarrinhoDeComprasDAO;
import br.unisinos.desenvsoft3.model.pedido.domain.CarrinhoDeCompras;
import br.unisinos.desenvsoft3.model.pedido.domain.CarrinhoDeComprasItem;
import br.unisinos.desenvsoft3.model.produto.dao.ProdutoDAO;
import br.unisinos.desenvsoft3.model.produto.domain.Produto;
import br.unisinos.desenvsoft3.service.generic.util.GenericResponse;

@Service
public class CarrinhoDeComprasService {

	@Autowired
	private CarrinhoDeComprasDAO carrinhoDeComprasDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	public CarrinhoDeComprasView getCarrinhoDeCompras(Integer idUsuario) {
		CarrinhoDeCompras carrinho = carrinhoDeComprasDAO.getByUsuario(idUsuario);
		
		if(carrinho != null) {
			return new CarrinhoDeComprasViewFactory().create(carrinho);
		} else {
			return new CarrinhoDeComprasView();
		}
	}

	public GenericResponse adicionarAoCarrinhoDeCompras(Integer idProduto, Integer idUsuario) {
		Produto produto = produtoDAO.carregar(idProduto);
		if(produto == null) {
			return GenericResponse.error("Produto inválido.");
		}
		
		CarrinhoDeCompras carrinho = carrinhoDeComprasDAO.getByUsuario(idUsuario);
		if(carrinho == null) {
			carrinho = new CarrinhoDeCompras();
			carrinho.setUsuario(usuarioDAO.carregar(idUsuario));
		}
		
		if(carrinho.getItens().stream().filter(item -> item.getProduto().getId().equals(idProduto)).count() == 0) {
			CarrinhoDeComprasItem item = new CarrinhoDeComprasItem();
			item.setCarrinhoDeCompras(carrinho);
			item.setProduto(produto);
			item.setQuantidade(1);
			carrinho.getItens().add(item);
			
			carrinhoDeComprasDAO.save(carrinho);
		}
		
		
		return GenericResponse.ok();
	}

	public GenericResponse removerDoCarrinhoDeCompras(Integer idProduto, Integer idUsuario) {
		Produto produto = produtoDAO.carregar(idProduto);
		if(produto == null) {
			return GenericResponse.error("Produto inválido.");
		}
		
		CarrinhoDeCompras carrinho = carrinhoDeComprasDAO.getByUsuario(idUsuario);
		if(carrinho != null) {
			carrinho.getItens().removeIf(item -> item.getProduto().getId().equals(idProduto));
			
			if(carrinho.getItens().isEmpty()) {
				carrinhoDeComprasDAO.delete(carrinho);
			} else {
				carrinhoDeComprasDAO.save(carrinho);
			}
		}
		
		return GenericResponse.ok();
	}

	public GenericResponse alterarQuantidadeDeProduto(Integer idProduto, Integer quantidade, Integer idUsuario) {
		if(quantidade < 0) {
			return GenericResponse.error("Quantidade não pode ser menor que zero.");
		}
		
		CarrinhoDeCompras carrinho = carrinhoDeComprasDAO.getByUsuario(idUsuario);
		if(carrinho != null) {
			CarrinhoDeComprasItem item = carrinho.getItens().stream().filter(i -> i.getProduto().getId().equals(idProduto)).findAny().orElse(null);
			
			if(item != null) {
				item.setQuantidade(quantidade);
				carrinhoDeComprasDAO.save(carrinho);
				return GenericResponse.ok();
			}
		}
		
		return GenericResponse.error("Produto não adicionado ao carrinho.");
	}
}
